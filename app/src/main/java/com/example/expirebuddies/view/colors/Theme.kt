package com.example.expirebuddies.view.colors


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = AppColorToken.PRIMARY,
    onPrimary = AppColorToken.ON_PRIMARY,
    secondary = AppColorToken.SURFACE_ALT,
    background = AppColorToken.DARK_BACKGROUND,
    surface = AppColorToken.SURFACE,
    onBackground = AppColorToken.TEXT_PRIMARY,
    onSurface = AppColorToken.TEXT_PRIMARY,
    error = AppColorToken.ERROR,
)

private val LightColorScheme = lightColorScheme(
    primary = AppColorToken.PRIMARY,
    onPrimary = AppColorToken.ON_PRIMARY,
    secondary = AppColorToken.SURFACE_ALT,
    background = AppColorToken.BACKGROUND,
    surface = AppColorToken.SURFACE,
    onBackground = AppColorToken.TEXT_PRIMARY,
    onSurface = AppColorToken.TEXT_PRIMARY,
    error = AppColorToken.ERROR,
)


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = {
            // Racchiudi tutto in Surface con background dal tema
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                content()
            }
        }
    )
}