package com.Elkood.ling_en4.ui.screens.truefalse

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.data.content.TrueFalseContent
import com.Elkood.ling_en4.ui.screens.quiz.QuizSound
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class TrueFalseQuizActivity : ComponentActivity() {

    private lateinit var sound: QuizSound
    private val engine by lazy {
        TrueFalseQuizEngine(
            questions = TrueFalseContent.quizPool,
            order = TrueFalseContent.quizPool.indices.shuffled(),
        )
    }
    private var latestScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sound = QuizSound(this)
        setContent {
            ZetaTheme {
                TrueFalseQuizScreen(
                    engine = engine,
                    onCorrect = { sound.playCorrect() },
                    onWrong = { sound.playWrong() },
                    onScoreChanged = { latestScore = it },
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences(TF_PREFS, Context.MODE_PRIVATE)
        val stored = prefs.getInt(TF_KEY, 0)
        if (shouldPersistHighScore(stored, latestScore)) {
            prefs.edit().putInt(TF_KEY, latestScore).apply()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sound.release()
    }
}
