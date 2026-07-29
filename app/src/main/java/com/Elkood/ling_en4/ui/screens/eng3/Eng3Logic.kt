package com.Elkood.ling_en4.ui.screens.eng3

const val ENG3_PREFS = "sharedprefs"
const val ENG3_KEY_HIGHSCORE = "keyhighscore"

/** Legacy label: "High Score :" concatenated with the value, with no space after the colon. */
fun formatHighScore(score: Int): String = "High Score :$score"
