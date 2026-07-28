package com.Elkood.ling_en4.ui.screens.paperquiz

/** Visual state of one option row after (or before) the "Check" reveal. */
enum class OptionVisual { Normal, Correct, Wrong }

/** Faithful to legacy: before reveal everything is Normal; after reveal the
 * correct option is Correct and EVERY other present option is Wrong,
 * independent of the user's own selection. */
fun optionVisual(index: Int, correctIndex: Int, revealed: Boolean): OptionVisual = when {
    !revealed -> OptionVisual.Normal
    index == correctIndex -> OptionVisual.Correct
    else -> OptionVisual.Wrong
}
