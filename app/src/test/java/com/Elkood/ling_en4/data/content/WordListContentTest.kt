package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.WordPair
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class WordListContentTest {
    @Test
    fun eachUnitHasItsPinnedCount() {
        assertEquals(52, WordListContent.unitWordsFor(8).size)
        assertEquals(69, WordListContent.unitWordsFor(9).size)
        assertEquals(0, WordListContent.unitWordsFor(10).size)
        assertEquals(120, WordListContent.unitWordsFor(11).size)
        assertEquals(51, WordListContent.unitWordsFor(12).size)
        assertEquals(106, WordListContent.unitWordsFor(13).size)
        assertEquals(35, WordListContent.unitWordsFor(14).size)
    }

    @Test
    fun invalidUnitReturnsEmptyList() {
        assertTrue(WordListContent.unitWordsFor(7).isEmpty())
        assertTrue(WordListContent.unitWordsFor(99).isEmpty())
    }

    @Test
    fun everyPairHasNonBlankEnglishAndArabic() {
        val nonEmptyUnits = listOf(8, 9, 11, 12, 13, 14)
        nonEmptyUnits.forEach { u ->
            WordListContent.unitWordsFor(u).forEach { p: WordPair ->
                assertTrue("unit $u had blank english", p.english.isNotBlank())
                assertTrue("unit $u had blank arabic", p.arabic.isNotBlank())
            }
        }
    }

    @Test
    fun indexHasSevenHeadersInUnitOrderWithVerbatimLabels() {
        assertEquals(7, WordListContent.indexHeaders.size)
        assertEquals(listOf(8, 9, 10, 11, 12, 13, 14), WordListContent.indexHeaders.map { it.unit })
        val h8 = WordListContent.headerFor(8)!!
        assertEquals("Unit 8", h8.title)
        assertEquals("عدد :  51", h8.countLabel)
        val h10 = WordListContent.headerFor(10)!!
        assertEquals("عدد :  0", h10.countLabel)
        val h14 = WordListContent.headerFor(14)!!
        assertEquals("عدد :  84", h14.countLabel)
    }

    @Test
    fun headerForUnknownUnitIsNull() {
        assertNull(WordListContent.headerFor(7))
    }
}
