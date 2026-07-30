package com.Elkood.ling_en4.ui.screens.truefalse

import com.Elkood.ling_en4.data.model.TrueFalseQuestion

const val TF_PREFS = "SaveScore"
const val TF_KEY = "tfscore"

/** Legacy contract: write the new score only when it beats the stored one. */
fun shouldPersistHighScore(stored: Int, new: Int): Boolean = new > stored

/**
 * Pure, testable True/False quiz mechanics: pick-without-repeat over [questions] in [order]
 * (defaults to source order; production injects a shuffle), score on correct answers only.
 */
class TrueFalseQuizEngine(
    private val questions: List<TrueFalseQuestion>,
    order: List<Int>? = null,
) {
    private val sequence: List<TrueFalseQuestion> =
        (order ?: questions.indices.toList()).map { questions[it] }
    private var index: Int = 0
    var score: Int = 0
        private set

    val total: Int get() = sequence.size

    fun current(): TrueFalseQuestion = sequence[index]

    fun hasNext(): Boolean = index < sequence.size - 1

    fun isFinished(): Boolean = index >= sequence.size

    /** Returns whether [choice] was correct; increments score on correct; advances the pointer. */
    fun answer(choice: Boolean): Boolean {
        val correct = choice == sequence[index].correctAnswer
        if (correct) score++
        index++
        return correct
    }
}
