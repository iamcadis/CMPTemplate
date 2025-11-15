package com.app.ui

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

/**
 * Creates and remembers a `TopAppBarScrollBehavior` that preserves the scroll state
 * for each destination in the navigation back stack.
 *
 * This behavior ensures that when a user navigates back to a previous screen, the
 * TopAppBar's scrolled position is restored. It also cleans up states for
 * destinations that are no longer in the back stack to prevent memory leaks.
 *
 * @return A `TopAppBarScrollBehavior` that is pinned and stateful across destinations.
 */
@Composable
fun NavController.rememberPinnedScrollBehavior(): TopAppBarScrollBehavior {
    val topAppBarStates = rememberSaveable { mutableStateMapOf<String, TopAppBarState>() }

    val backStackEntry by currentBackStackEntryAsState()
    val backStackEntries by currentBackStack.collectAsStateWithLifecycle()

    val currentRoute = backStackEntry?.destination?.route
    val defaultState = rememberTopAppBarState()
    val backStackRoutes = backStackEntries.mapNotNull { it.destination.route }.toSet()

    val topAppBarState = remember(currentRoute) {
        if (currentRoute.isNullOrBlank()) {
            defaultState
        } else {
            topAppBarStates.getOrPut(currentRoute) {
                TopAppBarState(
                    initialHeightOffsetLimit = -Float.MAX_VALUE,
                    initialHeightOffset = 0f,
                    initialContentOffset = 0f
                )
            }
        }
    }

    LaunchedEffect(backStackRoutes) {
        topAppBarStates.keys.retainAll { it in backStackRoutes }
    }

    return TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
}