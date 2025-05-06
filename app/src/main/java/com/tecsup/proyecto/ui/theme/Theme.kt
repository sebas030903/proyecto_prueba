package com.tecsup.proyecto.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun ProyectoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors()
    } else {
        lightColors()
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

private fun darkColors() = darkColorScheme(
    primary = Green500,
    secondary = Green200,
    surface = Black,
    onSurface = White,
    onPrimary = White,
    onSecondary = Black
)

private fun lightColors() = lightColorScheme(
    primary = Green700,
    secondary = Green200,
    surface = White,
    onSurface = Black,
    onPrimary = Black,
    onSecondary = White
)
