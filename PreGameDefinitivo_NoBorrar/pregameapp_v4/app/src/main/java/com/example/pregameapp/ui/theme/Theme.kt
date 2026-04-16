package com.example.pregameapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PreGameColors = darkColorScheme(
    primary            = NeonPurple,
    onPrimary          = Color.White,
    primaryContainer   = Color(0xFF4C1D95),
    secondary          = NeonViolet,
    onSecondary        = Color.White,
    background         = DeepBlack,
    surface            = SurfaceDark,
    onBackground       = TextPrimary,
    onSurface          = TextPrimary,
    outline            = BorderDark,
    error              = ImpostorRed
)

@Composable
fun PreGameTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PreGameColors,
        typography  = Typography(),
        content     = content
    )
}
