package com.Elkood.ling_en4.data

import com.Elkood.ling_en4.data.content.En4Vocabulary
import com.Elkood.ling_en4.data.model.Question
import com.Elkood.ling_en4.data.model.QuizTopic

data class QuizConfig(
    val topic: QuizTopic,
    val title: String,
    val questions: List<Question>,
    val passThreshold: Int,
    val highScorePrefFile: String,
    val highScorePrefKey: String,
)

fun quizConfigFor(topic: QuizTopic): QuizConfig = when (topic) {
    QuizTopic.VOCABULARY -> QuizConfig(
        topic = QuizTopic.VOCABULARY,
        title = "Vocabulary",
        questions = En4Vocabulary.questions,
        passThreshold = En4Vocabulary.questions.size,  // 45 — fixes legacy unreachable `score == 46`
        highScorePrefFile = "SaveScore",
        highScorePrefKey = "VocHighScore",
    )
    else -> TODO("Content for $topic is migrated in a later phase")
}
