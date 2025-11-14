package com.core.navigation.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import kotlin.jvm.JvmSuppressWildcards
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Immutable
data class ScreenEntry<T : Any>(
    val route: KClass<T>,
    val typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    val content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
)