package com.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmSuppressWildcards
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Stable
@Serializable
interface Screen {
    val showConfirmationOnLeave: Boolean
        get() = false

    @Composable
    fun getTitle(): String? {
        return null
    }
}

@Immutable
data class ScreenEntry<T : Any>(
    val route: KClass<T>,
    val typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    val content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
)

val LocalCurrentScreen = compositionLocalOf<Screen?> { null }