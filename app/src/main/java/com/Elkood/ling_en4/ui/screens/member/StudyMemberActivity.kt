package com.Elkood.ling_en4.ui.screens.member

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class StudyMemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZetaTheme {
                StudyMemberScreen()
            }
        }
    }
}
