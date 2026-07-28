package com.Elkood.ling_en4.ui.screens.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.ui.screens.shell.ShellActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZetaTheme {
                SplashScreen(onFinished = {
                    startActivity(Intent(this, ShellActivity::class.java))
                    finish()
                })
            }
        }
    }
}

@Composable
private fun SplashScreen(onFinished: () -> Unit) {
    val logoScale = remember { Animatable(0.6f) }
    val contentAlpha = remember { Animatable(0f) }
    var navigated by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        contentAlpha.animateTo(1f, tween(800))
        logoScale.animateTo(1f, tween(800))
        delay(1700)
        if (!navigated) {
            navigated = true
            onFinished()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_untitled),
            contentDescription = null,
            modifier = Modifier
                .size(160.dp)
                .scale(logoScale.value)
                .alpha(contentAlpha.value),
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = " Zeta",
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier.alpha(contentAlpha.value),
        )
    }
}
