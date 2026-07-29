package com.Elkood.ling_en4.ui.screens.statistics

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class StatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences(SCORE_PREFS, Context.MODE_PRIVATE)
        val state = statisticsFrom(
            voc = prefs.getInt("VocHighScore", 0),
            tf = prefs.getInt("tfscore", 0),
            full = prefs.getInt("fullscore", 0),
            numberPlay = prefs.getInt("numberPlay", 0),
            abber = prefs.getInt("AbberHighScore", 0),
            ext = prefs.getInt("ExtHighScore", 0),
            comp = prefs.getInt("CompHighScore", 0),
        )
        setContent {
            ZetaTheme {
                StatisticsScreen(state = state)
            }
        }
    }
}
