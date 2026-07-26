package com.Elkood.ling_en4.ui.screens.quiz

enum class AnswerPhase { ANSWERING, REVEALED }

data class QuizUiState(
    val questionNumber: Int,
    val totalQuestions: Int,
    val prompt: String,
    val options: List<String>,
    val selectedIndex: Int?,
    val correctIndex: Int?,
    val phase: AnswerPhase,
    val score: Int,
    val secondsLeft: Int,
    val isLastQuestion: Boolean,
    val finished: Boolean,
    val passed: Boolean,
)
