package com.Elkood.ling_en4.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.ui.theme.TajawalFontFamily

@Composable
fun HomeScreen(onStartExam: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.white_20)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_undraw_mathematics_4otb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
                .padding(30.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(colorResource(R.color.white)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "حول الاختبار الشامل",
                color = colorResource(R.color.colorPrimary),
                fontFamily = TajawalFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 5.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(1.dp)
                    .background(colorResource(R.color.grey_Light)),
            )
            Text(
                text = "يشمل جميع الأسئلة الموجودة في الدورات والأسئلة الذهبية إضافة إلى المقرر اللغة 4",
                textAlign = TextAlign.Center,
                fontFamily = TajawalFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(5.dp),
            )
            StartExamButton(
                text = "ابدأ الاختبار",
                onClick = onStartExam,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
            )
        }
    }
}

@Composable
private fun StartExamButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(20.dp)
    // Replicates legacy FButton: grey_Light face, backber text, 3dp blueberry bottom shadow.
    Box(modifier = modifier.background(color = colorResource(R.color.blueberry), shape = shape)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 3.dp)
                .clip(shape)
                .background(colorResource(R.color.grey_Light))
                .clickable(onClick = onClick)
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = colorResource(R.color.backber),
                fontFamily = TajawalFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }
}
