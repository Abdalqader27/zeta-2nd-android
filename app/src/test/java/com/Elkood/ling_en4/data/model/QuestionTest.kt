package com.Elkood.ling_en4.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class QuestionTest {
    @Test
    fun stores_prompt_options_and_zero_based_correct_index() {
        val q = Question("Website :", listOf("a", "b", "c", "d"), correctIndex = 1)
        assertEquals("Website :", q.prompt)
        assertEquals("b", q.options[q.correctIndex])
    }

    @Test
    fun rejects_wrong_option_count() {
        assertThrows(IllegalArgumentException::class.java) {
            Question("q", listOf("a", "b", "c"), correctIndex = 0)
        }
    }

    @Test
    fun rejects_out_of_range_correct_index() {
        assertThrows(IllegalArgumentException::class.java) {
            Question("q", listOf("a", "b", "c", "d"), correctIndex = 4)
        }
    }
}
