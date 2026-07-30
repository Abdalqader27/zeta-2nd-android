package com.Elkood.ling_en4.ui.screens.wordlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.content.WordListContent
import com.Elkood.ling_en4.data.model.WordUnitHeader

@Composable
fun WordIndexScreen(onUnitClick: (Int) -> Unit) {
    val muli = FontFamily(Font(R.font.muli))
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(WordListContent.indexHeaders) { header: WordUnitHeader ->
            UnitIndexCard(header, muli) { onUnitClick(header.unit) }
        }
    }
}

@Composable
private fun UnitIndexCard(header: WordUnitHeader, font: FontFamily, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(1.dp, colorResource(R.color.grey), RoundedCornerShape(6.dp))
            .background(colorResource(R.color.white), RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = header.title,
            color = colorResource(R.color.backber),
            fontFamily = font,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Text(
            text = header.countLabel,
            color = colorResource(R.color.backber),
            fontFamily = font,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}
