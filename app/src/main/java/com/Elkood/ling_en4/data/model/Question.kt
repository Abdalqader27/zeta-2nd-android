package com.Elkood.ling_en4.data.model

data class Question(
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
) {
    init {
        require(options.size == 4) { "A Question must have exactly 4 options, got ${options.size}" }
        require(correctIndex in options.indices) { "correctIndex $correctIndex out of range" }
    }
}
