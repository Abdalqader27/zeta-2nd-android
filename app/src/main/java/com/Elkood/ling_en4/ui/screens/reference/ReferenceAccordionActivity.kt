package com.Elkood.ling_en4.ui.screens.reference

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.data.content.ReferenceContent
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class ReferenceAccordionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val topic = ReferenceContent.referenceTopicFromExtra(intent.getStringExtra(EXTRA_TOPIC))
        setContent { ZetaTheme { ReferenceAccordionScreen(topic = topic) } }
    }

    companion object { const val EXTRA_TOPIC = "extra_topic" }
}
