package com.Elkood.ling_en4.ui.screens.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModel
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.QuizConfig
import com.Elkood.ling_en4.data.ResultMode
import com.Elkood.ling_en4.data.model.QuizTopic
import com.Elkood.ling_en4.data.quizConfigFor
import com.Elkood.ling_en4.ui.theme.ZetaTheme
import com.parse.Parse
import com.parse.ParseQuery

class ComposeQuizActivity : ComponentActivity() {

    companion object {
        const val EXTRA_TOPIC = "extra_topic"

        /** Score extra returned to the caller in INTENT_RESULT mode (ENG3). Value matches
         * the legacy QuizActivity_Eng3.EXTRA_SCORE so old_version reads it unchanged. */
        const val EXTRA_SCORE = "extrascore"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val topic = QuizTopic.valueOf(
            intent.getStringExtra(EXTRA_TOPIC) ?: QuizTopic.VOCABULARY.name
        )
        val config = quizConfigFor(topic, this)

        if (config.incrementPlayCount) incrementPlayCount()

        val seconds = countdownSeconds(config)

        val viewModel = ViewModelProvider(
            this,
            quizViewModelFactory(config, seconds),
        )[QuizViewModel::class.java]

        setContent {
            ZetaTheme {
                InteractiveQuizScreen(
                    viewModel = viewModel,
                    onFinished = { score, _ ->
                        onQuizFinished(config, score)
                        finish()
                    },
                    soundGated = config.soundGated,
                    showStreakBar = config.showStreakBar,
                )
            }
        }
    }

    /** Fixed override when configured (ENG3 = 40s); otherwise the saveR mapping:
     * 1 (default) -> 40s, 0 -> 30s, 2 -> 50s. */
    private fun countdownSeconds(config: QuizConfig): Int {
        config.fixedSeconds?.let { return it }
        return when (getSharedPreferences("saveData", Context.MODE_PRIVATE).getInt("saveR", 1)) {
            0 -> 30
            2 -> 50
            else -> 40
        }
    }

    private fun onQuizFinished(config: QuizConfig, score: Int) {
        when (config.resultMode) {
            ResultMode.INTENT_RESULT -> {
                setResult(RESULT_OK, Intent().putExtra(EXTRA_SCORE, score))
            }
            ResultMode.HIGH_SCORE_PREF -> {
                val prefs = getSharedPreferences(config.highScorePrefFile, Context.MODE_PRIVATE)
                if (prefs.getInt(config.highScorePrefKey, 0) < score) {
                    prefs.edit().putInt(config.highScorePrefKey, score).apply()
                    if (config.uploadToParse) uploadHighScore(score)
                }
            }
        }
    }

    /** Full Quiz: bump the play counter in SaveScore, mirroring the legacy onCreate. */
    private fun incrementPlayCount() {
        val prefs = getSharedPreferences("SaveScore", Context.MODE_PRIVATE)
        prefs.edit().putInt("numberPlay", prefs.getInt("numberPlay", 0) + 1).apply()
    }

    /** Full Quiz: push a new high score to the Back4App "Quiz" leaderboard row for this user. */
    private fun uploadHighScore(score: Int) {
        try {
            Parse.initialize(
                Parse.Configuration.Builder(this)
                    .applicationId(getString(R.string.back4app_app_id))
                    .clientKey(getString(R.string.back4app_client_key))
                    .server(getString(R.string.back4app_server_url))
                    .build()
            )
            val query = ParseQuery.getQuery<com.parse.ParseObject>("Quiz")
            val id = getSharedPreferences("SaveFile", Context.MODE_PRIVATE)
                .getString("objectID", "no")
            val name = getSharedPreferences("saveData", Context.MODE_PRIVATE)
                .getString("Name", "noName")
            query.getInBackground(id) { entity, e ->
                if (e == null && entity != null) {
                    entity.put("Highscore", score)
                    if (entity.get("Username") != name && name != null) {
                        entity.put("Username", name)
                    }
                    entity.saveInBackground()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show()
        }
    }
}

private fun quizViewModelFactory(config: QuizConfig, seconds: Int) = object : Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        QuizViewModel(config, totalSeconds = seconds) as T
}
