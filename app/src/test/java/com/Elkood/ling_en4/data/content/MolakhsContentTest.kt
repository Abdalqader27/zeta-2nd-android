package com.Elkood.ling_en4.data.content

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MolakhsContentTest {
    @Test
    fun hasSixSummariesWithVerbatimTrailingSpaceLabelsAndNoUnitTen() {
        assertEquals(6, MolakhsContent.summaries.size)
        assertEquals(
            listOf("Unit 8 ", "Unit 9 ", "Unit 11 ", "Unit 12 ", "Unit 13 ", "Unit 14 "),
            MolakhsContent.summaries.map { it.unitLabel },
        )
        assertFalse(MolakhsContent.summaries.any { it.unitLabel.trim() == "Unit 10" })
    }

    @Test
    fun everySummaryHasAtLeastOneCardWithContent() {
        MolakhsContent.summaries.forEach { summary ->
            assertTrue("${summary.unitLabel} had no cards", summary.cards.isNotEmpty())
            summary.cards.forEach { card ->
                assertTrue(card.english.isNotBlank() || card.arabic.isNotBlank())
            }
        }
    }
}
