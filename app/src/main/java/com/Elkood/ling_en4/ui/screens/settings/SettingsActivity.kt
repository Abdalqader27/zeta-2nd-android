package com.Elkood.ling_en4.ui.screens.settings

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE)
        setContent {
            ZetaTheme {
                val context = LocalContext.current
                var name by remember { mutableStateOf(prefs.getString(KEY_NAME, DEFAULT_NAME) ?: DEFAULT_NAME) }
                var nameEnabled by remember { mutableStateOf(false) }
                var saveVisible by remember { mutableStateOf(false) }
                var showWarning by remember { mutableStateOf(false) }
                var selectedDuration by remember { mutableIntStateOf(prefs.getInt(KEY_SAVE_R, DEFAULT_SAVE_R)) }
                var soundOn by remember { mutableStateOf(prefs.getBoolean(KEY_SWITCH1, DEFAULT_SWITCH1)) }

                SettingsScreen(
                    name = name,
                    onNameChange = { name = it },
                    nameEnabled = nameEnabled,
                    saveVisible = saveVisible,
                    selectedDuration = selectedDuration,
                    onDurationSelected = {
                        selectedDuration = it
                        prefs.edit().putInt(KEY_SAVE_R, it).apply()
                    },
                    soundOn = soundOn,
                    onSoundChange = {
                        soundOn = it
                        prefs.edit().putBoolean(KEY_SWITCH1, it).apply()
                    },
                    onEditNameClick = {
                        if (prefs.getBoolean(KEY_CHANGE_NAME, DEFAULT_CHANGE_NAME)) {
                            showWarning = true
                            nameEnabled = true
                            saveVisible = true
                        } else {
                            Toast.makeText(context, "لقد غيرت اسمك بلفعل ", Toast.LENGTH_LONG).show()
                        }
                    },
                    onSaveClick = {
                        if (prefs.getBoolean(KEY_CHANGE_NAME, DEFAULT_CHANGE_NAME)) {
                            prefs.edit()
                                .putBoolean(KEY_CHANGE_NAME, false)
                                .putString(KEY_NAME, name)
                                .apply()
                            saveVisible = false
                            nameEnabled = false
                        }
                    },
                    showWarning = showWarning,
                    onDismissWarning = { showWarning = false },
                )
            }
        }
    }
}
