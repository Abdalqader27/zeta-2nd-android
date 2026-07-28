package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PaperBuilderTest {
    @Test
    fun drops_absent_options_and_finds_correct_index() {
        val q = q("p", 0, 1, -1, -1, "a", "b", "c", "d")
        assertEquals(listOf("a", "b"), q.options)
        assertEquals(1, q.correctIndex)
    }

    @Test
    fun keeps_all_four_when_no_flag_is_minus_one() {
        val q = q("p", 1, 0, 0, 0, "a", "b", "c", "d")
        assertEquals(4, q.options.size)
        assertEquals(0, q.correctIndex)
    }

    @Test
    fun keeps_three_options() {
        val q = q("p", 0, 0, 1, -1, "a", "b", "c", "d")
        assertEquals(listOf("a", "b", "c"), q.options)
        assertEquals(2, q.correctIndex)
    }

    @Test
    fun rejects_when_no_correct_flag() {
        assertThrows(IllegalArgumentException::class.java) {
            q("p", 0, 0, -1, -1, "a", "b", "c", "d")
        }
    }
}
