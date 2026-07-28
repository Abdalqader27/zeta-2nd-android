package com.Elkood.ling_en4.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ExamPaperTest {
    @Test
    fun has_nine_variants_in_display_order() {
        assertEquals(
            listOf("R2018", "R2017", "R2017_ALT", "R2016", "R2016_ALT", "R2015", "R2014", "R2013", "R2012"),
            ExamPaper.entries.map { it.name },
        )
    }

    @Test
    fun carries_verbatim_card_copy() {
        assertEquals("دورة 2018 ", ExamPaper.R2018.cardTitle)
        assertEquals("الدورة الاولى فقط ", ExamPaper.R2018.cardSubtitle)
        assertEquals("الأولى ", ExamPaper.R2017.cardSubtitle)
        assertEquals("الثانية ", ExamPaper.R2017_ALT.cardSubtitle)
        assertEquals("الأولى ", ExamPaper.R2016.cardSubtitle)
        assertEquals("التكميلية", ExamPaper.R2016_ALT.cardSubtitle)
        assertEquals("الدورة الثالثة فقط ", ExamPaper.R2012.cardSubtitle)
    }

    @Test
    fun paper_holds_its_fields() {
        val p = Paper(ExamPaper.R2012, "h", "", "", listOf(PaperQuestion("q", listOf("a", "b"), 0)))
        assertEquals(ExamPaper.R2012, p.examPaper)
        assertEquals(1, p.questions.size)
    }
}
