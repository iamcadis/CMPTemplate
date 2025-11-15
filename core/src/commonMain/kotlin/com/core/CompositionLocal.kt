package com.core

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import com.core.navigation.ScreenConfigProvider
import com.core.ui.CustomSnackbarHostState

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("SnackbarHostState not found!")
}

val LocalScreenConfigProvider = compositionLocalOf<ScreenConfigProvider> {
    error("No FabConfigProvider found")
}

val LocalSnackbarHostState = staticCompositionLocalOf<CustomSnackbarHostState> {
    error("SnackbarHostState not found!")
}