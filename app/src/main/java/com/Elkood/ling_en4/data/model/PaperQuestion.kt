package com.Elkood.ling_en4.data.model

data class PaperQuestion(
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
) {
    init {
        require(options.size in 2..4) { "A PaperQuestion must have 2..4 options, got ${options.size}" }
        // correctIndex == -1 is the "no answer key" sentinel: the legacy Adapter_Quiz reveal
        // greens nothing and toasts for rows with no correct flag. Otherwise it must index an option.
        require(correctIndex == NO_ANSWER_KEY || correctIndex in options.indices) {
            "correctIndex $correctIndex out of range for ${options.size} options"
        }
    }

    companion object {
        /** Sentinel: this question has no correct-answer key (legacy reveals nothing). */
        const val NO_ANSWER_KEY = -1
    }
}
