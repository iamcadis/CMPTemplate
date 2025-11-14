package com.core.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import com.core.navigation.screen.Screen

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("SnackbarHostState not found!")
}

val LocalCurrentScreen = compositionLocalOf<Screen?> { null }