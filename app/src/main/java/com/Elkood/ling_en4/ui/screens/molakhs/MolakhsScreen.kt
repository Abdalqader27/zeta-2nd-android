package com.Elkood.ling_en4.ui.screens.molakhs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.content.MolakhsContent
import com.Elkood.ling_en4.data.model.MolakhsCard
import com.Elkood.ling_en4.data.model.MolakhsSummary
import kotlinx.coroutines.launch

@Composable
fun MolakhsScreen() {
    val summaries = MolakhsContent.summaries
    val pagerState = rememberPagerState(pageCount = { summaries.size })
    val scope = rememberCoroutineScope()
    val indicator = Color(0xFF0FB2C0)
    Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.white))) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            contentColor = indicator,
        ) {
            summaries.forEachIndexed { index, summary ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(summary.unitLabel) },
                )
            }
        }
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            SummaryPage(summaries[page])
        }
    }
}

@Composable
private fun SummaryPage(summary: MolakhsSummary) {
    val heading = FontFamily(Font(R.font.hugme))
    val body = FontFamily(Font(R.font.a5))
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(summary.cards) { card: MolakhsCard -> SummaryCard(card, heading, body) }
    }
}

@Composable
private fun SummaryCard(card: MolakhsCard, headingFont: FontFamily, bodyFont: FontFamily) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, colorResource(R.color.grey), RoundedCornerShape(6.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        card.title?.let {
            Text(
                text = it,
                fontFamily = headingFont,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(R.color.colorPrimary),
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
        Text(text = card.english, fontFamily = bodyFont, fontSize = 16.sp, textAlign = TextAlign.Center)
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = card.arabic, fontFamily = bodyFont, textAlign = TextAlign.Center)
    }
}
