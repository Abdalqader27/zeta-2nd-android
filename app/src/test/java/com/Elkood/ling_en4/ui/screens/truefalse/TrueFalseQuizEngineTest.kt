package com.Elkood.ling_en4.ui.screens.truefalse

import com.Elkood.ling_en4.data.model.TrueFalseQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TrueFalseQuizEngineTest {

    private val pool = listOf(
        TrueFalseQuestion("A", true),
        TrueFalseQuestion("B", false),
        TrueFalseQuestion("C", true),
    )

    @Test
    fun visitsEveryQuestionOnceWithoutRepeat() {
        val engine = TrueFalseQuizEngine(pool, order = listOf(0, 1, 2))
        val seen = mutableListOf<String>()
        while (!engine.isFinished()) {
            seen.add(engine.current().statement)
            engine.answer(true)
        }
        assertEquals(listOf("A", "B", "C"), seen)
        assertEquals(seen.toSet().size, seen.size)
    }

    @Test
    fun scoreIncrementsOnlyOnCorrect() {
        val engine = TrueFalseQuizEngine(pool, order = listOf(0, 1, 2))
        assertTrue(engine.answer(true))   // A is true -> correct
        assertTrue(engine.answer(true).not()) // B is false, answered true -> wrong
        assertTrue(engine.answer(true))   // C is true -> correct
        assertEquals(2, engine.score)
    }

    @Test
    fun finishesAfterAllQuestions() {
        val engine = TrueFalseQuizEngine(pool, order = listOf(0, 1, 2))
        assertFalse(engine.isFinished())
        repeat(3) { engine.answer(false) }
        assertTrue(engine.isFinished())
        assertFalse(engine.hasNext())
    }

    @Test
    fun highScorePersistsOnlyWhenBeaten() {
        assertTrue(shouldPersistHighScore(stored = 5, new = 6))
        assertFalse(shouldPersistHighScore(stored = 6, new = 6))
        assertFalse(shouldPersistHighScore(stored = 7, new = 6))
    }

    @Test
    fun prefsConstantsMatchLegacy() {
        assertEquals("SaveScore", TF_PREFS)
        assertEquals("tfscore", TF_KEY)
    }
}
