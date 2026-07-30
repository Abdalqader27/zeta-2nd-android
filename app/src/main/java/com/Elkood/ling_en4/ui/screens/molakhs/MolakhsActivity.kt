package com.Elkood.ling_en4.ui.screens.molakhs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class MolakhsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ZetaTheme { MolakhsScreen() } }
    }
}
