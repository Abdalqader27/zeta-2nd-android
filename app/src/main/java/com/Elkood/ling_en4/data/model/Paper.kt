package com.Elkood.ling_en4.data.model

data class Paper(
    val examPaper: ExamPaper,
    val headerTitle: String,
    val instruction: String,
    val passage: String,
    val questions: List<PaperQuestion>,
)
