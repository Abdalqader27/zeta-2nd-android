package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper
import com.Elkood.ling_en4.data.model.PaperQuestion
import kotlinx.serialization.json.Json

/**
 * Pure, platform-agnostic parse of a paper JSON document into the domain [Paper].
 *
 * The JSON is content-only — it carries no [ExamPaper] identity — so the caller supplies which
 * paper this document represents. Keeping this free of Android APIs lets the same parse run under
 * a plain JVM unit test that reads the asset file directly.
 */
object PaperParser {
    private val json = Json { ignoreUnknownKeys = true }

    fun parse(raw: String, examPaper: ExamPaper): Paper {
        val dto = json.decodeFromString<PaperJson>(raw)
        return Paper(
            examPaper = examPaper,
            headerTitle = dto.headerTitle,
            instruction = dto.instruction,
            passage = dto.passage,
            questions = dto.questions.map { q ->
                PaperQuestion(prompt = q.prompt, options = q.options, correctIndex = q.correctIndex)
            },
        )
    }
}
