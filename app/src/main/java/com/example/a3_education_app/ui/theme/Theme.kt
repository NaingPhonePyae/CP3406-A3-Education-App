package com.example.a3_education_app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = SpaceBlue,
    onPrimary = LightSurface,
    secondary = CosmicCyan,
    onSecondary = SpaceNavy,
    tertiary = SoftCoral,
    onTertiary = SpaceNavy,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnBackground,
    primaryContainer = StarGold,
    onPrimaryContainer = SpaceNavy
)

private val DarkColorScheme = darkColorScheme(
    primary = CosmicCyan,
    onPrimary = SpaceNavy,
    secondary = StarGold,
    onSecondary = SpaceNavy,
    tertiary = SoftCoral,
    onTertiary = SpaceNavy,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnBackground,
    primaryContainer = SpaceBlue,
    onPrimaryContainer = DarkOnBackground
)

@Composable
fun A3EducationAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}