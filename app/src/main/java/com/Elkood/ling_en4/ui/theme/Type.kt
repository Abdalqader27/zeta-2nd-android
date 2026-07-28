package com.Elkood.ling_en4.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.Elkood.ling_en4.R

val MuliFontFamily = FontFamily(
    Font(R.font.muli_regular, FontWeight.Normal),
    Font(R.font.muli_light, FontWeight.Light),
)

val ZetaTypography = Typography().let { base ->
    base.copy(
        bodyLarge = base.bodyLarge.copy(fontFamily = MuliFontFamily),
        bodyMedium = base.bodyMedium.copy(fontFamily = MuliFontFamily),
        titleLarge = base.titleLarge.copy(fontFamily = MuliFontFamily),
        labelLarge = base.labelLarge.copy(fontFamily = MuliFontFamily),
    )
}
