package com.Elkood.ling_en4.data.content

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class En4VocabularyTest {
    @Test
    fun has_forty_five_questions() {
        assertEquals(45, En4Vocabulary.questions.size)
    }

    @Test
    fun first_question_maps_answer_to_zero_based_index() {
        val q = En4Vocabulary.questions.first()
        assertTrue(q.prompt.startsWith("Website"))
        assertEquals(1, q.correctIndex)
        assertEquals("Collection of related webpages.", q.options[q.correctIndex])
    }

    @Test
    fun last_question_maps_answer_to_zero_based_index() {
        val q = En4Vocabulary.questions.last()
        assertTrue(q.prompt.startsWith("Markup Language"))
        assertEquals(0, q.correctIndex)
    }

    @Test
    fun every_question_has_valid_options() {
        En4Vocabulary.questions.forEach { assertEquals(4, it.options.size) }
    }
}
