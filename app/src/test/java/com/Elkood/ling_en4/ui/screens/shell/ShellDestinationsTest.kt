package com.Elkood.ling_en4.ui.screens.shell

import com.Elkood.ling_en4.R
import org.junit.Assert.assertEquals
import org.junit.Test

class ShellDestinationsTest {

    @Test
    fun tabs_have_expected_order_and_default() {
        assertEquals(3, SHELL_TABS.size)
        assertEquals("الدورات", SHELL_TABS[0].title)
        assertEquals("القائمة", SHELL_TABS[1].title)
        assertEquals("الرئيسية", SHELL_TABS[2].title)
        assertEquals(2, DEFAULT_TAB_INDEX)
    }

    @Test
    fun wave_mapping_matches_legacy() {
        assertEquals(R.drawable.guide_check_in_wave_4, waveFor(1))
        assertEquals(R.drawable.wave_5, waveFor(0))
        assertEquals(R.drawable.wave_5, waveFor(2))
        assertEquals(R.drawable.wave_5, SHELL_TABS[0].wave)
        assertEquals(R.drawable.guide_check_in_wave_4, SHELL_TABS[1].wave)
    }

    @Test
    fun drawer_items_match_order_titles_actions() {
        assertEquals(6, DRAWER_ITEMS.size)
        assertEquals(
            listOf(
                DrawerAction.SCORES, DrawerAction.HELP, DrawerAction.SHARE,
                DrawerAction.SETTINGS, DrawerAction.ABOUT, DrawerAction.ENG3
            ),
            DRAWER_ITEMS.map { it.action }
        )
        assertEquals("سجل النقاط   ", DRAWER_ITEMS[0].title)
        assertEquals("تحتاج لمساعدة ؟ ", DRAWER_ITEMS[1].title)
        assertEquals("مشاركة التطبيق ", DRAWER_ITEMS[2].title)
        assertEquals("الإعدادات", DRAWER_ITEMS[3].title)
        assertEquals("حول التطبيق", DRAWER_ITEMS[4].title)
        assertEquals(" إصدارات سابقة", DRAWER_ITEMS[5].title)
    }
}
