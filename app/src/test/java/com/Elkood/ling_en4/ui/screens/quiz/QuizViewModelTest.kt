package com.Elkood.ling_en4.ui.screens.quiz

import com.Elkood.ling_en4.data.QuizConfig
import com.Elkood.ling_en4.data.model.Question
import com.Elkood.ling_en4.data.model.QuizTopic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizViewModelTest {

    private fun config(pass: Int) = QuizConfig(
        topic = QuizTopic.VOCABULARY,
        title = "T",
        questions = listOf(
            Question("q1", listOf("a", "b", "c", "d"), correctIndex = 0),
            Question("q2", listOf("a", "b", "c", "d"), correctIndex = 1),
            Question("q3", listOf("a", "b", "c", "d"), correctIndex = 2),
        ),
        passThreshold = pass,
        highScorePrefFile = "f",
        highScorePrefKey = "k",
    )

    private fun vm(pass: Int = 3) =
        QuizViewModel(config(pass), totalSeconds = 40, shuffle = { it })

    @Test
    fun starts_on_first_question() {
        val s = vm().uiState.value
        assertEquals(1, s.questionNumber)
        assertEquals(3, s.totalQuestions)
        assertEquals("q1", s.prompt)
        assertEquals(AnswerPhase.ANSWERING, s.phase)
        assertEquals(0, s.score)
        assertEquals(40, s.secondsLeft)
    }

    @Test
    fun correct_answer_increments_score_and_reveals() {
        val vm = vm()
        vm.selectOption(0)
        vm.confirm()
        val s = vm.uiState.value
        assertEquals(1, s.score)
        assertEquals(AnswerPhase.REVEALED, s.phase)
        assertEquals(0, s.correctIndex)
    }

    @Test
    fun wrong_answer_does_not_increment_score() {
        val vm = vm()
        vm.selectOption(3)
        vm.confirm()
        assertEquals(0, vm.uiState.value.score)
    }

    @Test
    fun next_advances_and_resets_phase() {
        val vm = vm()
        vm.selectOption(0); vm.confirm(); vm.next()
        val s = vm.uiState.value
        assertEquals(2, s.questionNumber)
        assertEquals("q2", s.prompt)
        assertEquals(AnswerPhase.ANSWERING, s.phase)
        assertEquals(40, s.secondsLeft)
        assertEquals(null, s.selectedIndex)
    }

    @Test
    fun finishing_all_correct_sets_passed_true() {
        val vm = vm(pass = 3)
        vm.selectOption(0); vm.confirm(); vm.next()
        vm.selectOption(1); vm.confirm(); vm.next()
        vm.selectOption(2); vm.confirm()
        assertTrue(vm.uiState.value.isLastQuestion)
        vm.next()
        val s = vm.uiState.value
        assertTrue(s.finished)
        assertTrue(s.passed)
        assertEquals(3, s.score)
    }

    @Test
    fun finishing_below_threshold_sets_passed_false() {
        val vm = vm(pass = 3)
        vm.selectOption(0); vm.confirm(); vm.next()   // correct
        vm.selectOption(0); vm.confirm(); vm.next()   // wrong
        vm.selectOption(2); vm.confirm(); vm.next()   // correct -> score 2
        val s = vm.uiState.value
        assertTrue(s.finished)
        assertFalse(s.passed)
        assertEquals(2, s.score)
    }

    @Test
    fun time_expiry_reveals_without_scoring() {
        val vm = vm()
        vm.onTimeExpired()
        val s = vm.uiState.value
        assertEquals(AnswerPhase.REVEALED, s.phase)
        assertEquals(0, s.score)
        assertEquals(0, s.correctIndex)
    }

    @Test
    fun tick_decrements_and_expires_at_zero() {
        val vm = QuizViewModel(config(3), totalSeconds = 1, shuffle = { it })
        vm.tick()
        val s = vm.uiState.value
        assertEquals(0, s.secondsLeft)
        assertEquals(AnswerPhase.REVEALED, s.phase)
    }
}
