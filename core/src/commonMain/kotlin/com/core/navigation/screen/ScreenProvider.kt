package com.core.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf

@Immutable
data class ScreenProvider(
    val fab: @Composable (() -> Unit)? = null
)

interface ScreenConfigProvider {
    fun setProvider(provider: ScreenProvider)
}

val LocalScreenConfigProvider = compositionLocalOf<ScreenConfigProvider> {
    error("No FabConfigProvider found")
}