package com.Elkood.ling_en4.ui.screens.shell

import androidx.annotation.DrawableRes
import com.Elkood.ling_en4.R

enum class DrawerAction { SCORES, HELP, SHARE, SETTINGS, ABOUT, ENG3 }

data class DrawerItem(
    @DrawableRes val icon: Int,
    val title: String,
    val action: DrawerAction,
)

data class ShellTab(
    val title: String,
    @DrawableRes val wave: Int,
)

const val DEFAULT_TAB_INDEX = 2

@DrawableRes
fun waveFor(tabIndex: Int): Int =
    if (tabIndex == 1) R.drawable.guide_check_in_wave_4 else R.drawable.wave_5

val SHELL_TABS: List<ShellTab> = listOf(
    ShellTab("الدورات", waveFor(0)),
    ShellTab("القائمة", waveFor(1)),
    ShellTab("الرئيسية", waveFor(2)),
)

val DRAWER_ITEMS: List<DrawerItem> = listOf(
    DrawerItem(R.drawable.ic_diagram, "سجل النقاط   ", DrawerAction.SCORES),
    DrawerItem(R.drawable.ic_help_operator, "تحتاج لمساعدة ؟ ", DrawerAction.HELP),
    DrawerItem(R.drawable.ic_share, "مشاركة التطبيق ", DrawerAction.SHARE),
    DrawerItem(R.drawable.ic_settings, "الإعدادات", DrawerAction.SETTINGS),
    DrawerItem(R.drawable.ic_info_button, "حول التطبيق", DrawerAction.ABOUT),
    DrawerItem(R.drawable.brand, " إصدارات سابقة", DrawerAction.ENG3),
)
