package com.Elkood.ling_en4.data.content

import kotlinx.serialization.Serializable

/**
 * Wire-format DTOs for the `assets/papers/<name>.json` files. Kept separate from the domain
 * [com.Elkood.ling_en4.data.model.Paper] / [com.Elkood.ling_en4.data.model.PaperQuestion] so the
 * JSON schema stays a stable, platform-agnostic contract that can be shared across platforms.
 *
 * Schema: one file per paper — `{headerTitle, instruction, passage, questions:[{prompt, options, correctIndex}]}`.
 * `correctIndex` is 0-based into `options`; `-1` is the "no answer key" sentinel (legacy reveals nothing).
 * The paper's [com.Elkood.ling_en4.data.model.ExamPaper] identity is not stored in JSON — the loader supplies it.
 */
@Serializable
internal data class PaperJson(
    val headerTitle: String,
    val instruction: String,
    val passage: String,
    val questions: List<QuestionJson>,
)

@Serializable
internal data class QuestionJson(
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
)
