package com.Elkood.ling_en4.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PaperQuestionTest {
    @Test
    fun stores_prompt_options_and_correct_index() {
        val q = PaperQuestion("p", listOf("a", "b", "c"), correctIndex = 2)
        assertEquals("c", q.options[q.correctIndex])
    }

    @Test
    fun allows_two_options() {
        assertEquals(2, PaperQuestion("p", listOf("a", "b"), 0).options.size)
    }

    @Test
    fun rejects_fewer_than_two_options() {
        assertThrows(IllegalArgumentException::class.java) {
            PaperQuestion("p", listOf("a"), 0)
        }
    }

    @Test
    fun rejects_more_than_four_options() {
        assertThrows(IllegalArgumentException::class.java) {
            PaperQuestion("p", listOf("a", "b", "c", "d", "e"), 0)
        }
    }

    @Test
    fun rejects_out_of_range_correct_index() {
        assertThrows(IllegalArgumentException::class.java) {
            PaperQuestion("p", listOf("a", "b"), 2)
        }
    }

    @Test
    fun allows_no_answer_key_sentinel() {
        // Legacy Adapter_Quiz reveals nothing for rows with no correct flag; -1 encodes that.
        val q = PaperQuestion("p", listOf("a", "b"), PaperQuestion.NO_ANSWER_KEY)
        assertEquals(PaperQuestion.NO_ANSWER_KEY, q.correctIndex)
    }
}
