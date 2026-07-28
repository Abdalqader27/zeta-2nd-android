package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.PaperQuestion

/** Mirrors the legacy `Quiz(quiz, r1..r4_check, r1..r4_text)` constructor argument-for-argument.
 * Drops options whose check flag is -1 (absent rows) and sets correctIndex to the single
 * option whose flag is 1. */
internal fun q(
    prompt: String,
    r1Check: Int, r2Check: Int, r3Check: Int, r4Check: Int,
    r1Text: String, r2Text: String, r3Text: String, r4Text: String,
): PaperQuestion {
    val present = listOf(
        r1Check to r1Text,
        r2Check to r2Text,
        r3Check to r3Text,
        r4Check to r4Text,
    ).filter { it.first != -1 }
    require(present.count { it.first == 1 } == 1) {
        "Question must have exactly one correct option (flag==1): $prompt"
    }
    return PaperQuestion(
        prompt = prompt,
        options = present.map { it.second },
        correctIndex = present.indexOfFirst { it.first == 1 },
    )
}
