package com.Elkood.ling_en4.ui.screens.truefalse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.data.model.TrueFalseQuestion
import com.Elkood.ling_en4.ui.components.PrimaryButton

/**
 * [engine] is created and owned by the Activity. [onCorrect]/[onWrong] play sound.
 * [onScoreChanged] reports the running score so the Activity can persist the high score on exit.
 */
@Composable
fun TrueFalseQuizScreen(
    engine: TrueFalseQuizEngine,
    onCorrect: () -> Unit,
    onWrong: () -> Unit,
    onScoreChanged: (Int) -> Unit,
) {
    var questionNumber by remember { mutableIntStateOf(1) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(engine.isFinished()) }
    var dialog by remember { mutableStateOf<DialogState?>(null) }
    var currentStatement by remember { mutableStateOf(if (finished) "" else engine.current().statement) }

    if (finished) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Score : $score / ${engine.total}", fontSize = 24.sp)
        }
        return
    }

    fun submit(choice: Boolean) {
        val q: TrueFalseQuestion = engine.current()
        val correct = engine.answer(choice)
        if (correct) { score = engine.score; onScoreChanged(score); onCorrect() } else { onWrong() }
        dialog = DialogState(
            title = if (correct) "Correct!" else "Wrong...",
            message = "Answer : " + if (q.correctAnswer) "true" else "false",
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(text = "Q$questionNumber", fontSize = 20.sp)
        Text(text = "Score : $score", fontSize = 18.sp)
        Text(text = currentStatement, fontSize = 22.sp, textAlign = TextAlign.Center)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            PrimaryButton(text = "TRUE", onClick = { submit(true) })
            PrimaryButton(text = "FALSE", onClick = { submit(false) })
        }
    }

    dialog?.let { state ->
        AlertDialog(
            onDismissRequest = { /* non-cancelable: no-op, matches legacy setCancelable(false) */ },
            title = { Text(state.title) },
            text = { Text(state.message) },
            confirmButton = {
                TextButton(onClick = {
                    dialog = null
                    if (engine.isFinished()) {
                        finished = true
                    } else {
                        questionNumber += 1
                        currentStatement = engine.current().statement
                    }
                }) { Text("OK") }
            },
        )
    }
}

private data class DialogState(val title: String, val message: String)
