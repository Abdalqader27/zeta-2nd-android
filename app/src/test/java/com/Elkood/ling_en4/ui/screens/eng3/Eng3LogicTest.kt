package com.Elkood.ling_en4.ui.screens.eng3

import org.junit.Assert.assertEquals
import org.junit.Test

class Eng3LogicTest {
    @Test
    fun formatsHighScoreWithNoSpaceAfterColon() {
        assertEquals("High Score :0", formatHighScore(0))
        assertEquals("High Score :42", formatHighScore(42))
    }

    @Test
    fun prefsConstantsMatchLegacy() {
        assertEquals("sharedprefs", ENG3_PREFS)
        assertEquals("keyhighscore", ENG3_KEY_HIGHSCORE)
    }
}
