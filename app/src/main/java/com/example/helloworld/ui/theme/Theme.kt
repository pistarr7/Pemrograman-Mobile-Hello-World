package com.example.helloworld.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * MINGGU 1 — Tema Aplikasi dengan Material Design 3
 *
 * Material3 mendukung Dynamic Color (Android 12+) yang otomatis
 * menyesuaikan warna dengan wallpaper pengguna.
 */

// Skema warna Light Mode
private val LightColorScheme = lightColorScheme(
    primary = AndroidGreen,
    onPrimary = White,
    primaryContainer = GreenContainer,
    onPrimaryContainer = DarkGreen,
    secondary = DarkGray,
    onSecondary = White,
    background = LightBackground,
    surface = White,
    onBackground = DarkText,
    onSurface = DarkText,
)

// Skema warna Dark Mode
private val DarkColorScheme = darkColorScheme(
    primary = AndroidGreenDark,
    onPrimary = DarkGreen,
    primaryContainer = DarkGreen,
    onPrimaryContainer = GreenContainer,
    secondary = LightGray,
    onSecondary = DarkText,
    background = DarkBackground,
    surface = DarkSurface,
    onBackground = LightText,
    onSurface = LightText,
)

@Composable
fun HelloWorldTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color tersedia di Android 12+ (API 31)
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Gunakan Dynamic Color jika tersedia (Android 12+)
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Update status bar color agar sesuai tema
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
