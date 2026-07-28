package com.Elkood.ling_en4.ui.screens.shell

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class ShellActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userName = getSharedPreferences("saveData", Context.MODE_PRIVATE)
            .getString("Name", "") ?: ""
        setContent {
            ZetaTheme {
                ShellScreen(
                    activity = this,
                    userName = userName,
                    onExit = { finishAffinity() },
                )
            }
        }
    }
}
