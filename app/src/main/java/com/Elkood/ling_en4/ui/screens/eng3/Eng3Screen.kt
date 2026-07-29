package com.Elkood.ling_en4.ui.screens.eng3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.ui.components.PrimaryButton

@Composable
fun Eng3Screen(highScore: Int, onStart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.brand),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorResource(R.color.colorPrimary)),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
        )
        Text(text = "Eng 3 ", fontSize = 30.sp)
        Text(text = "اختبارات أسئلة دورات لغة 3  ")
        Text(text = formatHighScore(highScore), modifier = Modifier.padding(vertical = 8.dp))
        PrimaryButton(text = "lets Start", onClick = onStart)
    }
}
