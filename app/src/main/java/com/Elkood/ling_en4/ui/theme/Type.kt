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

val TajawalFontFamily = FontFamily(
    Font(R.font.tajawal_regular, FontWeight.Normal),
    Font(R.font.tajawal_light, FontWeight.Light),
    Font(R.font.tajawal_medium, FontWeight.Medium),
    Font(R.font.tajawal_bold, FontWeight.Bold),
)

val ZetaTypography = Typography().let { base ->
    base.copy(
        bodyLarge = base.bodyLarge.copy(fontFamily = MuliFontFamily),
        bodyMedium = base.bodyMedium.copy(fontFamily = MuliFontFamily),
        titleLarge = base.titleLarge.copy(fontFamily = MuliFontFamily),
        labelLarge = base.labelLarge.copy(fontFamily = MuliFontFamily),
    )
}
