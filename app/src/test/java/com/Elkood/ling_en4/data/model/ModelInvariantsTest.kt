package com.Elkood.ling_en4.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class ModelInvariantsTest {
    @Test
    fun wordPairHoldsBothLanguages() {
        val p = WordPair(english = "book", arabic = "كتاب")
        assertEquals("book", p.english)
        assertEquals("كتاب", p.arabic)
    }

    @Test
    fun referenceTopicHasExactlyFourTopics() {
        assertEquals(4, ReferenceTopic.entries.size)
        assertEquals(
            listOf("VOCABULARY", "ABBREVIATIONS", "COMPOUND_NOUNS", "EXTENSIONS"),
            ReferenceTopic.entries.map { it.name },
        )
    }

    @Test
    fun molakhsSummaryRejectsEmptyCardList() {
        assertThrows(IllegalArgumentException::class.java) {
            MolakhsSummary(unitLabel = "Unit 8 ", cards = emptyList())
        }
    }

    @Test
    fun molakhsSummaryAllowsNonEmptyCards() {
        val s = MolakhsSummary(
            unitLabel = "Unit 8 ",
            cards = listOf(MolakhsCard(title = "T", english = "e", arabic = "ع")),
        )
        assertEquals(1, s.cards.size)
        assertTrue(s.cards.first().title == "T")
    }

    @Test
    fun trueFalseItemAndQuestionCarryBoolean() {
        assertTrue(TrueFalseItem("e", "ع", isTrue = true).isTrue)
        assertTrue(TrueFalseQuestion("s", correctAnswer = false).correctAnswer.not())
    }
}
