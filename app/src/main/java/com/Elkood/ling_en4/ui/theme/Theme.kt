package com.Elkood.ling_en4.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ZetaColorScheme = lightColorScheme(
    primary = ZetaColors.Primary,
    onPrimary = ZetaColors.Surface,
    secondary = ZetaColors.Accent,
    background = ZetaColors.Surface,
    surface = ZetaColors.Surface,
    onSurface = ZetaColors.OnSurface,
)

@Composable
fun ZetaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ZetaColorScheme,
        typography = ZetaTypography,
        content = content,
    )
}
