package com.Elkood.ling_en4.ui.screens.statistics

import org.junit.Assert.assertEquals
import org.junit.Test

class StatisticsUiStateTest {
    @Test
    fun fullScoreDrivesTrueFalseAndMark() {
        val s = statisticsFrom(voc = 0, tf = 0, full = 421, numberPlay = 0, abber = 0, ext = 0, comp = 0)
        assertEquals(421, s.fullTrue)
        assertEquals(0, s.fullFalse)
        assertEquals(100f, s.mark, 0.0001f)
    }

    @Test
    fun zeroFullScoreGivesAllWrongAndZeroMark() {
        val s = statisticsFrom(voc = 0, tf = 0, full = 0, numberPlay = 0, abber = 0, ext = 0, comp = 0)
        assertEquals(0, s.fullTrue)
        assertEquals(421, s.fullFalse)
        assertEquals(0f, s.mark, 0.0001f)
    }

    @Test
    fun partialFullScoreUsesAbsAnd421Denominator() {
        val s = statisticsFrom(voc = 5, tf = 6, full = 210, numberPlay = 3, abber = 7, ext = 8, comp = 9)
        assertEquals(210, s.fullTrue)
        assertEquals(211, s.fullFalse) // abs(421 - 210)
        assertEquals((100 * 210) / 421f, s.mark, 0.0001f)
        // passthrough fields
        assertEquals(5, s.voc)
        assertEquals(6, s.tf)
        assertEquals(3, s.numberPlay)
        assertEquals(7, s.abber)
        assertEquals(8, s.ext)
        assertEquals(9, s.comp)
    }
}
