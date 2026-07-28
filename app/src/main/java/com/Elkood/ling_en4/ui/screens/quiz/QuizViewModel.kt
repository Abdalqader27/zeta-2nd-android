package com.Elkood.ling_en4.ui.screens.quiz

import androidx.lifecycle.ViewModel
import com.Elkood.ling_en4.data.QuizConfig
import com.Elkood.ling_en4.data.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel(
    private val config: QuizConfig,
    private val totalSeconds: Int,
    shuffle: (List<Question>) -> List<Question> = { it.shuffled() },
) : ViewModel() {

    private val questions: List<Question> = shuffle(config.questions)
    private var index = 0
    private var score = 0

    private val _uiState = MutableStateFlow(buildState(AnswerPhase.ANSWERING, null, totalSeconds))
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private val current: Question get() = questions[index]

    private fun buildState(phase: AnswerPhase, selected: Int?, seconds: Int) = QuizUiState(
        questionNumber = index + 1,
        totalQuestions = questions.size,
        prompt = current.prompt,
        options = current.options,
        selectedIndex = selected,
        correctIndex = if (phase == AnswerPhase.REVEALED) current.correctIndex else null,
        phase = phase,
        score = score,
        secondsLeft = seconds,
        isLastQuestion = index == questions.size - 1,
        finished = false,
        passed = false,
    )

    fun selectOption(optionIndex: Int) {
        if (_uiState.value.phase != AnswerPhase.ANSWERING) return
        _uiState.value = _uiState.value.copy(selectedIndex = optionIndex)
    }

    /** Grades the current question, reveals the answer. */
    fun confirm(): Boolean {
        if (_uiState.value.phase != AnswerPhase.ANSWERING) return false
        val correct = _uiState.value.selectedIndex == current.correctIndex
        if (correct) score++
        reveal()
        return correct
    }

    /** Called when the countdown reaches zero with no confirm. */
    fun onTimeExpired() {
        if (_uiState.value.phase != AnswerPhase.ANSWERING) return
        if (_uiState.value.selectedIndex == current.correctIndex) score++
        reveal()
    }

    private fun reveal() {
        _uiState.value = _uiState.value.copy(
            phase = AnswerPhase.REVEALED,
            correctIndex = current.correctIndex,
            score = score,
        )
    }

    /** Advances to the next question, or finishes the quiz. */
    fun next() {
        if (index == questions.size - 1) {
            _uiState.value = _uiState.value.copy(
                finished = true,
                passed = score >= config.passThreshold,
                score = score,
            )
            return
        }
        index++
        _uiState.value = buildState(AnswerPhase.ANSWERING, selected = null, seconds = totalSeconds)
    }

    /** One-second countdown tick; expires the question when it hits zero. */
    fun tick() {
        val s = _uiState.value
        if (s.phase != AnswerPhase.ANSWERING) return
        val remaining = (s.secondsLeft - 1).coerceAtLeast(0)
        _uiState.value = s.copy(secondsLeft = remaining)
        if (remaining == 0) onTimeExpired()
    }
}
