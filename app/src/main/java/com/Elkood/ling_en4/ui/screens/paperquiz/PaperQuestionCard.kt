package com.Elkood.ling_en4.ui.screens.paperquiz

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.model.PaperQuestion
import com.Elkood.ling_en4.ui.theme.MuliFontFamily
import com.Elkood.ling_en4.ui.theme.ZetaColors

@Composable
fun PaperQuestionCard(
    question: PaperQuestion,
    selectedIndex: Int?,
    revealed: Boolean,
    onSelect: (Int) -> Unit,
    onCheck: () -> Unit,
) {
    var showSelectPrompt by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 6.dp)) {
        Text(
            text = question.prompt,
            color = ZetaColors.QuestionText,
            fontFamily = MuliFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ZetaColors.QuestionBg, RoundedCornerShape(20.dp))
                .padding(8.dp),
        ) {
            question.options.forEachIndexed { index, option ->
                val visual = optionVisual(index, question.correctIndex, revealed)
                val textColor = when (visual) {
                    OptionVisual.Normal -> ZetaColors.OptionText
                    OptionVisual.Correct -> ZetaColors.Correct
                    OptionVisual.Wrong -> ZetaColors.WrongText
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedIndex == index,
                            enabled = !revealed,
                            onClick = { showSelectPrompt = false; onSelect(index) },
                        )
                        .padding(vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = selectedIndex == index,
                        enabled = !revealed,
                        onClick = { showSelectPrompt = false; onSelect(index) },
                    )
                    Text(
                        text = option,
                        color = textColor,
                        fontFamily = MuliFontFamily,
                        modifier = Modifier.weight(1f).padding(start = 4.dp),
                    )
                    if (visual == OptionVisual.Correct) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check_green),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                    } else if (visual == OptionVisual.Wrong) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cancel_mark),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                    }
                }
            }
        }

        if (showSelectPrompt && !revealed) {
            Text(
                text = "يرجى اختيار الجواب",
                color = ZetaColors.WrongText,
                fontFamily = MuliFontFamily,
                modifier = Modifier.padding(top = 4.dp),
            )
        }

        if (!revealed) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .background(ZetaColors.Accent, RoundedCornerShape(20.dp))
                    .clickable {
                        when {
                            selectedIndex == null -> showSelectPrompt = true
                            // Legacy: a checked row with no answer key falls to the
                            // else-branch — toast, colors nothing, stays interactive.
                            question.correctIndex == PaperQuestion.NO_ANSWER_KEY -> {
                                showSelectPrompt = false
                                Toast.makeText(
                                    context,
                                    "للأ سف ماعرفنا حل هاد السؤال ",
                                    Toast.LENGTH_LONG,
                                ).show()
                            }
                            else -> onCheck()
                        }
                    }
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_check_green),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
                Text(
                    text = "Check ",
                    color = ZetaColors.Surface,
                    fontFamily = MuliFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 6.dp),
                )
            }
        }
    }
}
