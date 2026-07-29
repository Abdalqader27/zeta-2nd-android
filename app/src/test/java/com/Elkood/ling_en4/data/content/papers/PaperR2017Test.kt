package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaperR2017Test {
    @Test
    fun has_expected_question_count() {
        assertEquals(54, paperR2017.questions.size)
    }

    @Test
    fun every_question_has_valid_shape() {
        paperR2017.questions.forEach { q ->
            assertTrue("options 2..4", q.options.size in 2..4)
            assertTrue("correctIndex in range", q.correctIndex in q.options.indices)
            assertTrue("prompt non-blank", q.prompt.isNotBlank())
        }
    }
}
