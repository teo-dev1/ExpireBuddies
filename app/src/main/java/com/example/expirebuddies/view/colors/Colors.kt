package com.example.expirebuddies.view.colors

import androidx.compose.ui.graphics.Color

// Colori base
val BLACK = Color(0xFF000113)
val LIGHT_BLUE_WHITE = Color(0xFFF1F5F9)
val BLUE_GRAY = Color(0xFF334155)
val ORANGE = Color(0xFFF56D09)
val SURFACE_ALT_COLOR = Color(0xFFF8FAFC)
val PRIMARY_VARIANT_COLOR = Color(0xFF253042)
val ON_PRIMARY_COLOR = Color(0xFFFFFFFF)
val TEXT_SECONDARY_COLOR = Color(0xFF4D4D4D)
val ERROR_COLOR = Color(0xFFB00020)

// AppColorToken.kt
object AppColorToken {
    // backgrounds
    val BACKGROUND = ORANGE
    val DARK_BACKGROUND = BLUE_GRAY

    // surfaces
    val SURFACE = LIGHT_BLUE_WHITE
    val SURFACE_ALT = SURFACE_ALT_COLOR

    // primary / accent
    val PRIMARY = BLUE_GRAY
    val PRIMARY_VARIANT = PRIMARY_VARIANT_COLOR
    val ON_PRIMARY = ON_PRIMARY_COLOR

    // testo
    val TEXT_PRIMARY = BLACK
    val TEXT_SECONDARY = TEXT_SECONDARY_COLOR

    // altri utili
    val ERROR = ERROR_COLOR
}
