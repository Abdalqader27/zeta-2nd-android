package com.Elkood.ling_en4.data.model

data class PaperQuestion(
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
) {
    init {
        require(options.size in 2..4) { "A PaperQuestion must have 2..4 options, got ${options.size}" }
        require(correctIndex in options.indices) { "correctIndex $correctIndex out of range for ${options.size} options" }
    }
}
