package com.Elkood.ling_en4.ui.screens.paperquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.ui.theme.TajawalFontFamily

@Composable
fun PaperListScreen(onPaperClick: (ExamPaper) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(4.dp),
    ) {
        items(ExamPaper.entries) { examPaper ->
            PaperCard(examPaper = examPaper, onClick = { onPaperClick(examPaper) })
        }
    }
}

@Composable
private fun PaperCard(examPaper: ExamPaper, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(15.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_folder),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.height(70.dp).padding(top = 10.dp),
        )
        Text(
            text = examPaper.cardTitle,
            fontFamily = TajawalFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = examPaper.cardSubtitle,
            color = Color(0xFFCCCCCC),
            fontFamily = TajawalFontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(5.dp),
        )
    }
}
