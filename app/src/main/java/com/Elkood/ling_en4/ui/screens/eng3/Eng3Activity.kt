package com.Elkood.ling_en4.ui.screens.eng3

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.Elkood.ling_en4.data.model.QuizTopic
import com.Elkood.ling_en4.ui.screens.quiz.ComposeQuizActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class Eng3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences(ENG3_PREFS, Context.MODE_PRIVATE)
        setContent {
            ZetaTheme {
                var highScore by remember { mutableIntStateOf(prefs.getInt(ENG3_KEY_HIGHSCORE, 0)) }
                val launcher = rememberLauncherForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        val score = result.data?.getIntExtra(ComposeQuizActivity.EXTRA_SCORE, 0) ?: 0
                        if (score > highScore) {
                            highScore = score
                            prefs.edit().putInt(ENG3_KEY_HIGHSCORE, score).apply()
                        }
                    }
                }
                Eng3Screen(
                    highScore = highScore,
                    onStart = {
                        val intent = Intent(this, ComposeQuizActivity::class.java)
                        intent.putExtra(ComposeQuizActivity.EXTRA_TOPIC, QuizTopic.ENG3.name)
                        launcher.launch(intent)
                    },
                )
            }
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Quiz")
            .setMessage("Do You Want To Close Your App ?")
            .setCancelable(true)
            .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
            .setPositiveButton("Yes") { _, _ -> finish() }
            .show()
    }
}
