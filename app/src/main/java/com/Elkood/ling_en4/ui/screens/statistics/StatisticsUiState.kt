package com.Elkood.ling_en4.ui.screens.statistics

import kotlin.math.abs

const val SCORE_PREFS = "SaveScore"

data class StatisticsUiState(
    val voc: Int,
    val tf: Int,
    val fullTrue: Int,
    val fullFalse: Int,
    val mark: Float,
    val numberPlay: Int,
    val abber: Int,
    val ext: Int,
    val comp: Int,
)

/** Mirrors the legacy statistics activity: fullFalse = |421 - full|, mark = (100 * full) / 421f. */
fun statisticsFrom(
    voc: Int,
    tf: Int,
    full: Int,
    numberPlay: Int,
    abber: Int,
    ext: Int,
    comp: Int,
): StatisticsUiState = StatisticsUiState(
    voc = voc,
    tf = tf,
    fullTrue = full,
    fullFalse = abs(421 - full),
    mark = (100 * full) / 421f,
    numberPlay = numberPlay,
    abber = abber,
    ext = ext,
    comp = comp,
)
