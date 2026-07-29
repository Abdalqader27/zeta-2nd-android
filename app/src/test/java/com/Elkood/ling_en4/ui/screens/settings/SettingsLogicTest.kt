package com.Elkood.ling_en4.ui.screens.settings

import org.junit.Assert.assertEquals
import org.junit.Test

class SettingsLogicTest {
    @Test
    fun durationOptionsAreThirtyFortyFifty() {
        assertEquals(listOf("30", "40", "50"), DURATION_OPTIONS)
    }

    @Test
    fun durationLabelMapsIndexToOption() {
        assertEquals("30", durationLabelFor(0))
        assertEquals("40", durationLabelFor(1))
        assertEquals("50", durationLabelFor(2))
    }

    @Test
    fun outOfRangeIndexFallsBackToDefault() {
        assertEquals("40", durationLabelFor(99))
        assertEquals("40", durationLabelFor(-1))
        assertEquals(1, DEFAULT_SAVE_R)
    }

    @Test
    fun prefsConstantsAndDefaultsMatchLegacy() {
        assertEquals("saveData", SETTINGS_PREFS)
        assertEquals("saveR", KEY_SAVE_R)
        assertEquals("switch1", KEY_SWITCH1)
        assertEquals("Name", KEY_NAME)
        assertEquals("changeName", KEY_CHANGE_NAME)
        assertEquals("No Name", DEFAULT_NAME)
    }
}
