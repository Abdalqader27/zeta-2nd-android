package com.Elkood.ling_en4.data

import android.content.Context
import com.Elkood.ling_en4.data.content.QuizContentRepository
import com.Elkood.ling_en4.data.model.Question
import com.Elkood.ling_en4.data.model.QuizTopic

/** How the quiz reports its result when it finishes. */
enum class ResultMode {
    /** Persist a per-topic high score in SharedPreferences (Vocabulary/Abberv/Comp/Ext/Full). */
    HIGH_SCORE_PREF,

    /** Return the score to the launching Activity via setResult (ENG3). */
    INTENT_RESULT,
}

data class QuizConfig(
    val topic: QuizTopic,
    val title: String,
    val questions: List<Question>,
    val passThreshold: Int,
    /** High-score store — used only when [resultMode] == HIGH_SCORE_PREF. */
    val highScorePrefFile: String,
    val highScorePrefKey: String,
    /** Fixed countdown in seconds; when null the activity uses the saveR preference mapping. */
    val fixedSeconds: Int? = null,
    /** When false, correct/wrong sounds always play regardless of the switch1 preference (ENG3). */
    val soundGated: Boolean = true,
    val resultMode: ResultMode = ResultMode.HIGH_SCORE_PREF,
    /** Show the consecutive-correct streak bar (Full Quiz). */
    val showStreakBar: Boolean = false,
    /** Increment the "numberPlay" counter in SaveScore on launch (Full Quiz). */
    val incrementPlayCount: Boolean = false,
    /** Upload a new high score to the Back4App "Quiz" leaderboard (Full Quiz). */
    val uploadToParse: Boolean = false,
)

fun quizConfigFor(topic: QuizTopic, context: Context): QuizConfig {
    val questions = QuizContentRepository.questionsFor(topic, context)
    return when (topic) {
        QuizTopic.VOCABULARY -> QuizConfig(
            topic = topic,
            title = "Vocabulary",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "SaveScore",
            highScorePrefKey = "VocHighScore",
        )

        QuizTopic.ABBREVIATIONS -> QuizConfig(
            topic = topic,
            title = "Abbreviations",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "SaveScore",
            highScorePrefKey = "AbberHighScore",
        )

        QuizTopic.COMPOUND_NOUNS -> QuizConfig(
            topic = topic,
            title = "Compound Nouns",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "SaveScore",
            highScorePrefKey = "CompHighScore",
        )

        QuizTopic.EXTENSIONS -> QuizConfig(
            topic = topic,
            title = "Extensions",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "SaveScore",
            highScorePrefKey = "ExtHighScore",
        )

        QuizTopic.ENG3 -> QuizConfig(
            topic = topic,
            title = "ENG3",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "",           // unused: ENG3 returns its score to the caller
            highScorePrefKey = "",
            fixedSeconds = 40,
            soundGated = false,
            resultMode = ResultMode.INTENT_RESULT,
        )

        QuizTopic.FULL_QUIZ -> QuizConfig(
            topic = topic,
            title = "Full Quiz",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "SaveScore",
            highScorePrefKey = "fullscore",
            showStreakBar = true,
            incrementPlayCount = true,
            uploadToParse = true,
        )
    }
}
