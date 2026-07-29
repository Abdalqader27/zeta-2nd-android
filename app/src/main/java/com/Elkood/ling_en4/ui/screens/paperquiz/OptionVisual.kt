package com.Elkood.ling_en4.ui.screens.paperquiz

import com.Elkood.ling_en4.data.model.PaperQuestion

/** Visual state of one option row after (or before) the "Check" reveal. */
enum class OptionVisual { Normal, Correct, Wrong }

/** Faithful to legacy: before reveal everything is Normal; after reveal the
 * correct option is Correct and EVERY other present option is Wrong,
 * independent of the user's own selection.
 *
 * When there is no answer key (correctIndex == NO_ANSWER_KEY), legacy colored
 * nothing (it fell to the else-branch: toast, no reveal). Mirror that here by
 * keeping every option Normal even if revealed is somehow true. */
fun optionVisual(index: Int, correctIndex: Int, revealed: Boolean): OptionVisual = when {
    !revealed -> OptionVisual.Normal
    correctIndex == PaperQuestion.NO_ANSWER_KEY -> OptionVisual.Normal
    index == correctIndex -> OptionVisual.Correct
    else -> OptionVisual.Wrong
}
