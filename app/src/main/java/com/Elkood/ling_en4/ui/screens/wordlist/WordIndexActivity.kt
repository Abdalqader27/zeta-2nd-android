package com.Elkood.ling_en4.ui.screens.wordlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class WordIndexActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZetaTheme {
                WordIndexScreen(onUnitClick = { unit ->
                    startActivity(
                        Intent(this, UnitWordsActivity::class.java)
                            .putExtra(UnitWordsActivity.EXTRA_UNIT, unit),
                    )
                })
            }
        }
    }
}
