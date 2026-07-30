package com.Elkood.ling_en4.data.model

import androidx.annotation.ColorRes

data class ReferenceItem(
    val title: String,
    val englishBody: String,
    val arabicBody: String,
    @ColorRes val colorRes: Int,
)
