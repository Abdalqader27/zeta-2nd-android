package com.Elkood.ling_en4.ui.screens.wordlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.content.WordListContent
import com.Elkood.ling_en4.data.model.WordPair

@Composable
fun UnitWordsScreen(unit: Int) {
    val english = FontFamily(Font(R.font.a5))
    val arabic = FontFamily(Font(R.font.tajawal_bold))
    val words = WordListContent.unitWordsFor(unit)
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(words) { pair: WordPair -> WordRow(pair, english, arabic) }
    }
}

@Composable
private fun WordRow(pair: WordPair, englishFont: FontFamily, arabicFont: FontFamily) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        WordCell(pair.english, englishFont, Modifier.weight(1f))
        WordCell(pair.arabic, arabicFont, Modifier.weight(1f))
    }
}

@Composable
private fun WordCell(text: String, font: FontFamily, modifier: Modifier) {
    Box(
        modifier = modifier
            .height(56.dp)
            .background(colorResource(R.color.white), RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text, fontFamily = font, fontWeight = FontWeight.Bold)
    }
}
