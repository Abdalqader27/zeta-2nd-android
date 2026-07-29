package com.Elkood.ling_en4.data.content

import android.content.Context
import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

/**
 * Loads a paper's content from `assets/papers/<name>.json` and parses it via [PaperParser].
 *
 * The asset filename is the enum name lower-cased (R2017_ALT -> `papers/r2017_alt.json`), matching
 * the deterministic converter that generated the files. Mirrors the existing
 * [QuizContentRepository] loader idiom (object singleton + [Context] + `assets`).
 */
object PaperRepository {

    fun assetPath(examPaper: ExamPaper): String = "papers/${examPaper.name.lowercase()}.json"

    fun loadPaper(context: Context, examPaper: ExamPaper): Paper {
        val raw = context.assets.open(assetPath(examPaper)).bufferedReader().use { it.readText() }
        return PaperParser.parse(raw, examPaper)
    }
}
