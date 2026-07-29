package com.Elkood.ling_en4.ui.screens.paperquiz

import com.Elkood.ling_en4.data.model.PaperQuestion
import org.junit.Assert.assertEquals
import org.junit.Test

class OptionVisualTest {
    @Test
    fun normal_for_all_indices_before_reveal() {
        assertEquals(OptionVisual.Normal, optionVisual(index = 0, correctIndex = 2, revealed = false))
        assertEquals(OptionVisual.Normal, optionVisual(index = 2, correctIndex = 2, revealed = false))
    }

    @Test
    fun correct_only_for_correct_index_after_reveal() {
        assertEquals(OptionVisual.Correct, optionVisual(index = 2, correctIndex = 2, revealed = true))
    }

    @Test
    fun wrong_for_other_indices_after_reveal_regardless_of_user_pick() {
        assertEquals(OptionVisual.Wrong, optionVisual(index = 0, correctIndex = 2, revealed = true))
        assertEquals(OptionVisual.Wrong, optionVisual(index = 1, correctIndex = 2, revealed = true))
        assertEquals(OptionVisual.Wrong, optionVisual(index = 3, correctIndex = 2, revealed = true))
    }

    @Test
    fun normal_for_all_indices_when_no_answer_key_even_if_revealed() {
        val noKey = PaperQuestion.NO_ANSWER_KEY
        assertEquals(OptionVisual.Normal, optionVisual(index = 0, correctIndex = noKey, revealed = true))
        assertEquals(OptionVisual.Normal, optionVisual(index = 1, correctIndex = noKey, revealed = true))
        assertEquals(OptionVisual.Normal, optionVisual(index = 3, correctIndex = noKey, revealed = true))
    }
}
