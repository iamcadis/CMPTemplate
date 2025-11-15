package com.design.system

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.design.system.foundation.backgroundDark
import com.design.system.foundation.backgroundLight
import com.design.system.foundation.errorContainerDark
import com.design.system.foundation.errorContainerLight
import com.design.system.foundation.errorDark
import com.design.system.foundation.errorLight
import com.design.system.foundation.inverseOnSurfaceDark
import com.design.system.foundation.inverseOnSurfaceLight
import com.design.system.foundation.inversePrimaryDark
import com.design.system.foundation.inversePrimaryLight
import com.design.system.foundation.inverseSurfaceDark
import com.design.system.foundation.inverseSurfaceLight
import com.design.system.foundation.onBackgroundDark
import com.design.system.foundation.onBackgroundLight
import com.design.system.foundation.onErrorContainerDark
import com.design.system.foundation.onErrorContainerLight
import com.design.system.foundation.onErrorDark
import com.design.system.foundation.onErrorLight
import com.design.system.foundation.onPrimaryContainerDark
import com.design.system.foundation.onPrimaryContainerLight
import com.design.system.foundation.onPrimaryDark
import com.design.system.foundation.onPrimaryLight
import com.design.system.foundation.onSecondaryContainerDark
import com.design.system.foundation.onSecondaryContainerLight
import com.design.system.foundation.onSecondaryDark
import com.design.system.foundation.onSecondaryLight
import com.design.system.foundation.onSurfaceDark
import com.design.system.foundation.onSurfaceLight
import com.design.system.foundation.onSurfaceVariantDark
import com.design.system.foundation.onSurfaceVariantLight
import com.design.system.foundation.onTertiaryContainerDark
import com.design.system.foundation.onTertiaryContainerLight
import com.design.system.foundation.onTertiaryDark
import com.design.system.foundation.onTertiaryLight
import com.design.system.foundation.outlineDark
import com.design.system.foundation.outlineLight
import com.design.system.foundation.outlineVariantDark
import com.design.system.foundation.outlineVariantLight
import com.design.system.foundation.primaryContainerDark
import com.design.system.foundation.primaryContainerLight
import com.design.system.foundation.primaryDark
import com.design.system.foundation.primaryLight
import com.design.system.foundation.scrimDark
import com.design.system.foundation.scrimLight
import com.design.system.foundation.secondaryContainerDark
import com.design.system.foundation.secondaryContainerLight
import com.design.system.foundation.secondaryDark
import com.design.system.foundation.secondaryLight
import com.design.system.foundation.surfaceBrightDark
import com.design.system.foundation.surfaceBrightLight
import com.design.system.foundation.surfaceContainerDark
import com.design.system.foundation.surfaceContainerHighDark
import com.design.system.foundation.surfaceContainerHighLight
import com.design.system.foundation.surfaceContainerHighestDark
import com.design.system.foundation.surfaceContainerHighestLight
import com.design.system.foundation.surfaceContainerLight
import com.design.system.foundation.surfaceContainerLowDark
import com.design.system.foundation.surfaceContainerLowLight
import com.design.system.foundation.surfaceContainerLowestDark
import com.design.system.foundation.surfaceContainerLowestLight
import com.design.system.foundation.surfaceDark
import com.design.system.foundation.surfaceDimDark
import com.design.system.foundation.surfaceDimLight
import com.design.system.foundation.surfaceLight
import com.design.system.foundation.surfaceVariantDark
import com.design.system.foundation.surfaceVariantLight
import com.design.system.foundation.tertiaryContainerDark
import com.design.system.foundation.tertiaryContainerLight
import com.design.system.foundation.tertiaryDark
import com.design.system.foundation.tertiaryLight

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

@Composable
fun AppTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkMode) darkScheme else lightScheme

    CompositionLocalProvider {
        MaterialTheme(colorScheme = colorScheme, content = content)
    }
}