package com.app.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.core.navigation.screen.Screen
import com.core.navigation.screen.ScreenEntry
import kotlinx.collections.immutable.PersistentList

@Suppress("ParamsComparedByRef")
@Composable
fun getCurrentScreen(
    navController: NavController,
    screenEntries: PersistentList<ScreenEntry<out Screen>>
): Screen? {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val state: State<Screen?> = produceState(initialValue = null, backStackEntry) {
        value = backStackEntry?.let { entry ->
            screenEntries.firstOrNull { screen ->
                entry.destination.hasRoute(screen.route)
            }?.let { screen ->
                runCatching { entry.toRoute(screen.route) as? Screen }.getOrNull()
            }
        }
    }

    return state.value
}