package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper
import com.Elkood.ling_en4.data.model.PaperQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * Reads every `assets/papers/<name>.json` straight off disk (the `testDebugUnitTest` CWD is the module
 * `app/` dir) and parses it through the production [PaperParser]. This is the fidelity gate for the
 * JSON pivot: per-paper question counts, shape invariants, and the hand-verified answer-key
 * anomalies all come from the legacy Java `new Quiz(...)` sources via the deterministic converter.
 */
class PaperParserTest {

    private fun load(examPaper: ExamPaper): Paper {
        val raw = File("src/main/assets/${PaperRepository.assetPath(examPaper)}").readText()
        return PaperParser.parse(raw, examPaper)
    }

    /** Question counts = `new Quiz(...)` calls in each legacy `En4*Constants.java`. */
    private val expectedCounts = mapOf(
        ExamPaper.R2018 to 60,
        ExamPaper.R2017 to 54,
        ExamPaper.R2017_ALT to 60,
        ExamPaper.R2016 to 60,
        ExamPaper.R2016_ALT to 60,
        ExamPaper.R2015 to 60,
        ExamPaper.R2014 to 60,
        ExamPaper.R2013 to 60,
        ExamPaper.R2012 to 54,
    )

    @Test
    fun every_paper_has_an_asset_that_parses() {
        ExamPaper.values().forEach { p ->
            val paper = load(p)
            assertEquals("examPaper identity for $p", p, paper.examPaper)
            assertTrue("headerTitle non-blank for $p", paper.headerTitle.isNotBlank())
            assertTrue("questions present for $p", paper.questions.isNotEmpty())
        }
    }

    @Test
    fun question_counts_match_legacy_sources() {
        expectedCounts.forEach { (p, n) ->
            assertEquals("question count for $p", n, load(p).questions.size)
        }
    }

    @Test
    fun every_question_has_valid_shape() {
        ExamPaper.values().forEach { p ->
            load(p).questions.forEachIndexed { i, q ->
                assertTrue("$p #$i options 2..4", q.options.size in 2..4)
                assertTrue("$p #$i prompt non-blank", q.prompt.isNotBlank())
                assertTrue(
                    "$p #$i correctIndex $q.correctIndex out of range",
                    q.correctIndex == PaperQuestion.NO_ANSWER_KEY || q.correctIndex in q.options.indices,
                )
            }
        }
    }

    @Test
    fun zero_answer_key_rows_use_sentinel() {
        // 5 legacy rows carry no correct flag -> correctIndex == -1 (reveal nothing + toast).
        fun sentinels(p: ExamPaper) = load(p).questions.count { it.correctIndex == PaperQuestion.NO_ANSWER_KEY }
        assertEquals("R2015 sentinel rows", 4, sentinels(ExamPaper.R2015))
        assertEquals("R2016_ALT sentinel rows", 1, sentinels(ExamPaper.R2016_ALT))
        val total = ExamPaper.values().sumOf { sentinels(it) }
        assertEquals("total sentinel rows across all papers", 5, total)
    }

    @Test
    fun multi_correct_rows_take_first_flag() {
        // Legacy Adapter_Quiz greens only the FIRST option whose flag == 1.
        assertEquals("R2017_ALT #5 first-flag", 2, load(ExamPaper.R2017_ALT).questions[5].correctIndex)
        assertEquals("R2014 #44 first-flag", 0, load(ExamPaper.R2014).questions[44].correctIndex)
    }
}
