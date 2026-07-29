package com.Elkood.ling_en4.ui.screens.statistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R

@Composable
fun StatisticsScreen(state: StatisticsUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_diagram),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
        )
        SectionHeader(icon = R.drawable.ic_058_bunny, title = "الشامل")
        StatRow(label = "الإجابات الصحيحة", value = "${state.fullTrue}", outOf = "421")
        StatRow(label = "الإجابات الخاطئة", value = "${state.fullFalse}", outOf = "421")
        StatRow(label = " مرات اللعب", value = "${state.numberPlay}", outOf = null)
        StatRow(label = " علاماتك ", value = state.mark.toString(), outOf = "100")

        SectionHeader(icon = R.drawable.triviafinal, title = "الجزئي")
        StatRow(label = "المردفات", value = "${state.voc}", outOf = "46")
        StatRow(label = "صح أو خطأ ", value = "${state.tf}", outOf = "36")
        StatRow(label = "الاختصارات", value = "${state.abber}", outOf = "23")
        StatRow(label = "لواحق المواقع ", value = "${state.ext}", outOf = "20")
        StatRow(label = " الأسماء المركبة  ", value = "${state.comp}", outOf = "20")
    }
}

@Composable
private fun SectionHeader(icon: Int, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(icon), contentDescription = null, modifier = Modifier.height(32.dp))
        Text(text = title, fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
private fun StatRow(label: String, value: String, outOf: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
    ) {
        Text(text = label, modifier = Modifier.weight(1f))
        Text(text = if (outOf != null) "$value / $outOf" else value)
    }
}
