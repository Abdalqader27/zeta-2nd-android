package com.Elkood.ling_en4.ui.screens.quiz

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.Elkood.ling_en4.ui.components.PrimaryButton
import com.Elkood.ling_en4.ui.theme.MuliFontFamily
import com.Elkood.ling_en4.ui.theme.ZetaColors
import kotlinx.coroutines.delay

@Composable
fun InteractiveQuizScreen(
    viewModel: QuizViewModel,
    onFinished: (score: Int, passed: Boolean) -> Unit,
    soundGated: Boolean = true,
    showStreakBar: Boolean = false,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val sound = rememberQuizSound(context, soundGated)

    // Countdown loop: one tick per second while answering.
    LaunchedEffect(state.questionNumber, state.phase) {
        while (state.phase == AnswerPhase.ANSWERING) {
            delay(1000)
            viewModel.tick()
        }
    }

    // Terminal navigation.
    LaunchedEffect(state.finished) {
        if (state.finished) onFinished(state.score, state.passed)
    }

    // Full Quiz: celebrate a 10-in-a-row streak once, matching the legacy tempBar == 10 toast.
    LaunchedEffect(state.streak) {
        if (showStreakBar && state.streak == 10) {
            Toast.makeText(context, "أحسنت 10 إجابات صحيحة متتالية", Toast.LENGTH_SHORT).show()
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ZetaColors.Surface)
                    .padding(bottom = 66.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                QuizHeaderRow(state)

                if (showStreakBar) {
                    LinearProgressIndicator(
                        progress = { (state.streak.coerceAtMost(10)) / 10f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 4.dp),
                    )
                }

                Text(
                    text = " Check the correct answer : ",
                    fontFamily = MuliFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp),
                )

                Text(
                    text = state.prompt,
                    fontFamily = MuliFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = ZetaColors.QuestionText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 3.dp)
                        .background(ZetaColors.QuestionBg)
                        .padding(10.dp),
                )

                OptionsGroup(state, onSelect = viewModel::selectOption)
            }

            PrimaryButton(
                text = when {
                    state.phase == AnswerPhase.ANSWERING -> "Confirm"
                    state.isLastQuestion -> "Finish"
                    else -> "Next"
                },
                onClick = {
                    if (state.phase == AnswerPhase.ANSWERING) {
                        if (viewModel.confirm()) sound.playCorrect() else sound.playWrong()
                    } else {
                        viewModel.next()
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun QuizHeaderRow(state: QuizUiState) {
    Row(modifier = Modifier.fillMaxWidth().padding(2.dp)) {
        InfoCard(title = "Quiz", value = "${state.questionNumber} / ${state.totalQuestions}", modifier = Modifier.weight(1f))
        InfoCard(
            title = "Timer",
            value = formatSeconds(state.secondsLeft),
            valueColor = if (state.secondsLeft < 15) ZetaColors.WrongText else ZetaColors.OnSurface,
            modifier = Modifier.weight(1f),
        )
        InfoCard(title = "Point", value = state.score.toString(), modifier = Modifier.weight(1f))
    }
}

@Composable
private fun InfoCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = ZetaColors.OnSurface,
) {
    Card(modifier = modifier.padding(2.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                fontFamily = MuliFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().background(ZetaColors.HeaderBg).padding(4.dp),
            )
            Text(
                text = value,
                fontFamily = MuliFontFamily,
                fontWeight = FontWeight.Bold,
                color = valueColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(4.dp),
            )
        }
    }
}

@Composable
private fun OptionsGroup(state: QuizUiState, onSelect: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        state.options.forEachIndexed { i, option ->
            if (option.isEmpty()) return@forEachIndexed
            val color = when {
                state.phase == AnswerPhase.REVEALED && i == state.correctIndex -> ZetaColors.Correct
                state.phase == AnswerPhase.REVEALED -> ZetaColors.WrongText
                else -> ZetaColors.OptionText
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = state.selectedIndex == i,
                        enabled = state.phase == AnswerPhase.ANSWERING,
                        onClick = { onSelect(i) },
                    )
                    .padding(4.dp),
            ) {
                RadioButton(
                    selected = state.selectedIndex == i,
                    enabled = state.phase == AnswerPhase.ANSWERING,
                    onClick = { onSelect(i) },
                )
                Text(
                    text = option,
                    color = color,
                    fontFamily = MuliFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

private fun formatSeconds(totalSeconds: Int): String {
    val m = totalSeconds / 60
    val s = totalSeconds % 60
    return "%02d:%02d".format(m, s)
}

@Composable
private fun rememberQuizSound(context: android.content.Context, gated: Boolean): QuizSound {
    val sound = remember(gated) { QuizSound(context, gated) }
    DisposableEffect(Unit) { onDispose { sound.release() } }
    return sound
}
