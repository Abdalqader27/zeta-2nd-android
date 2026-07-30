package com.Elkood.ling_en4.data.content

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TrueFalseContentTest {
    @Test
    fun listHasThirtySixItemsSplitEighteenEighteen() {
        assertEquals(36, TrueFalseContent.listItems.size)
        assertEquals(18, TrueFalseContent.listItems.count { it.isTrue })
        assertEquals(18, TrueFalseContent.listItems.count { !it.isTrue })
    }

    @Test
    fun everyListItemHasBothLanguages() {
        TrueFalseContent.listItems.forEach {
            assertTrue(it.english.isNotBlank())
            assertTrue(it.arabic.isNotBlank())
        }
    }

    @Test
    fun quizPoolHasThirtyFiveQuestionsWithNonBlankStatements() {
        assertEquals(35, TrueFalseContent.quizPool.size)
        TrueFalseContent.quizPool.forEach { assertTrue(it.statement.isNotBlank()) }
    }
}
