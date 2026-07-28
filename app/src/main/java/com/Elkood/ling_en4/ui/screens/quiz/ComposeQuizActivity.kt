package com.Elkood.ling_en4.ui.screens.quiz

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModel
import com.Elkood.ling_en4.data.QuizConfig
import com.Elkood.ling_en4.data.model.QuizTopic
import com.Elkood.ling_en4.data.quizConfigFor
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class ComposeQuizActivity : ComponentActivity() {

    companion object {
        const val EXTRA_TOPIC = "extra_topic"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val topic = QuizTopic.valueOf(
            intent.getStringExtra(EXTRA_TOPIC) ?: QuizTopic.VOCABULARY.name
        )
        val config = quizConfigFor(topic)
        val seconds = countdownSeconds()

        val viewModel = ViewModelProvider(
            this,
            quizViewModelFactory(config, seconds),
        )[QuizViewModel::class.java]

        setContent {
            ZetaTheme {
                InteractiveQuizScreen(
                    viewModel = viewModel,
                    onFinished = { score, _ ->
                        saveHighScore(config, score)
                        finish()
                    },
                )
            }
        }
    }

    /** Original saveR mapping: 1 (default) -> 40s, 0 -> 30s, 2 -> 50s. */
    private fun countdownSeconds(): Int =
        when (getSharedPreferences("saveData", Context.MODE_PRIVATE).getInt("saveR", 1)) {
            0 -> 30
            2 -> 50
            else -> 40
        }

    private fun saveHighScore(config: QuizConfig, score: Int) {
        val prefs = getSharedPreferences(config.highScorePrefFile, Context.MODE_PRIVATE)
        if (prefs.getInt(config.highScorePrefKey, 0) < score) {
            prefs.edit().putInt(config.highScorePrefKey, score).apply()
        }
    }
}

private fun quizViewModelFactory(config: QuizConfig, seconds: Int) = object : Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        QuizViewModel(config, totalSeconds = seconds) as T
}
