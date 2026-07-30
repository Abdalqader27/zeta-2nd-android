package com.Elkood.ling_en4.data.model

data class MolakhsSummary(val unitLabel: String, val cards: List<MolakhsCard>) {
    init { require(cards.isNotEmpty()) { "MolakhsSummary must have at least one card" } }
}
