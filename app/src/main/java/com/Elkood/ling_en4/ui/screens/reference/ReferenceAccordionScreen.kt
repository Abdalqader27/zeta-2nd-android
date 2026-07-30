package com.Elkood.ling_en4.ui.screens.reference

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.content.ReferenceContent
import com.Elkood.ling_en4.data.model.ReferenceItem
import com.Elkood.ling_en4.data.model.ReferenceTopic

@Composable
fun ReferenceAccordionScreen(topic: ReferenceTopic) {
    val items = ReferenceContent.itemsFor(topic)
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items) { item: ReferenceItem -> AccordionRow(item) }
    }
}

@Composable
private fun AccordionRow(item: ReferenceItem) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, colorResource(R.color.grey), RoundedCornerShape(6.dp))
            .background(colorResource(R.color.white), RoundedCornerShape(6.dp))
            .clickable { expanded = !expanded }
            .padding(12.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_clubs_word_smash_icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(item.colorRes)),
                modifier = Modifier.size(24.dp),
            )
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
            )
            Image(
                painter = painterResource(if (expanded) R.mipmap.less else R.mipmap.more),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
        }
        AnimatedVisibility(visible = expanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text(text = item.englishBody)
                Text(text = item.arabicBody, modifier = Modifier.padding(top = 6.dp))
            }
        }
    }
}
