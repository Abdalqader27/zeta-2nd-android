package com.Elkood.ling_en4.ui.screens.settings

const val SETTINGS_PREFS = "saveData"
const val KEY_SAVE_R = "saveR"
const val KEY_SWITCH1 = "switch1"
const val KEY_NAME = "Name"
const val KEY_CHANGE_NAME = "changeName"

const val DEFAULT_SAVE_R = 1
const val DEFAULT_SWITCH1 = true
const val DEFAULT_NAME = "No Name"
const val DEFAULT_CHANGE_NAME = true

/** Quiz-duration radio options; the stored saveR is the selected index into this list. */
val DURATION_OPTIONS: List<String> = listOf("30", "40", "50")

fun durationLabelFor(saveR: Int): String =
    DURATION_OPTIONS.getOrElse(saveR) { DURATION_OPTIONS[DEFAULT_SAVE_R] }
