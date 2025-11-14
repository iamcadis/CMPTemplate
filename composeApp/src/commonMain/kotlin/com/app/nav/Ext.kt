package com.app.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.core.navigation.Screen
import com.core.navigation.ScreenEntry
import kotlinx.collections.immutable.PersistentList

@Composable
internal fun NavController.currentScreenAsState(
    screenEntries: PersistentList<ScreenEntry<out Screen>>
): State<Screen?> {
    val backStackEntry by currentBackStackEntryAsState()

    return produceState(initialValue = null, backStackEntry) {
        value = backStackEntry?.let { entry ->
            screenEntries.firstOrNull { screen ->
                entry.destination.hasRoute(screen.route)
            }?.let { screen ->
                runCatching { entry.toRoute(screen.route) as? Screen }.getOrNull()
            }
        }
    }
}