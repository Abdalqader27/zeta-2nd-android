package com.Elkood.ling_en4.ui.screens.wordlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class UnitWordsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unit = intent.getIntExtra(EXTRA_UNIT, -1)
        setContent { ZetaTheme { UnitWordsScreen(unit = unit) } }
    }

    companion object { const val EXTRA_UNIT = "extra_unit" }
}
