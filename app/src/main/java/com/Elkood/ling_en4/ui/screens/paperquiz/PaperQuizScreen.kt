package com.Elkood.ling_en4.ui.screens.paperquiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.data.model.Paper
import com.Elkood.ling_en4.ui.theme.MuliFontFamily
import com.Elkood.ling_en4.ui.theme.ZetaColors

@Composable
fun PaperQuizScreen(paper: Paper, onBack: () -> Unit) {
    BackHandler(onBack = onBack)

    val selected = rememberSaveable(
        paper.examPaper,
        saver = listSaver(save = { it.toList() }, restore = { it.toMutableStateList() }),
    ) { MutableList(paper.questions.size) { -1 }.toMutableStateList() }

    val revealed = rememberSaveable(
        paper.examPaper,
        saver = listSaver(save = { it.toList() }, restore = { it.toMutableStateList() }),
    ) { MutableList(paper.questions.size) { false }.toMutableStateList() }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp)) {
            item {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
            item {
                Text(
                    text = paper.headerTitle,
                    fontFamily = MuliFontFamily,
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                )
            }
            if (paper.instruction.isNotEmpty()) {
                item {
                    Text(
                        text = paper.instruction,
                        fontFamily = MuliFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 8.dp),
                    )
                }
            }
            if (paper.passage.isNotEmpty()) {
                item {
                    Text(
                        text = paper.passage,
                        color = ZetaColors.OnSurface,
                        fontFamily = MuliFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ZetaColors.Surface)
                            .padding(6.dp),
                    )
                }
            }
            itemsIndexed(paper.questions) { index, question ->
                PaperQuestionCard(
                    question = question,
                    selectedIndex = selected[index].takeIf { it >= 0 },
                    revealed = revealed[index],
                    onSelect = { selected[index] = it },
                    onCheck = { revealed[index] = true },
                )
            }
        }
    }
}
