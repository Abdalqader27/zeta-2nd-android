package com.Elkood.ling_en4.ui.screens.truefalse

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.content.TrueFalseContent
import com.Elkood.ling_en4.data.model.TrueFalseItem

@Composable
fun TrueFalseListScreen() {
    val english = FontFamily(Font(R.font.muli))
    val arabic = FontFamily(Font(R.font.a3))
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(TrueFalseContent.listItems) { item: TrueFalseItem -> TrueFalseRow(item, english, arabic) }
    }
}

@Composable
private fun TrueFalseRow(item: TrueFalseItem, englishFont: FontFamily, arabicFont: FontFamily) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, colorResource(R.color.blueberry), RoundedCornerShape(6.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(if (item.isTrue) R.drawable.ic_check_green else R.drawable.ic_cancel_mark),
            contentDescription = null,
            modifier = Modifier.weight(3f).size(40.dp),
        )
        Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
            Text(
                text = item.english,
                fontFamily = englishFont,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(R.color.backber),
            )
            Text(text = item.arabic, fontFamily = arabicFont, modifier = Modifier.padding(top = 4.dp))
        }
    }
}
