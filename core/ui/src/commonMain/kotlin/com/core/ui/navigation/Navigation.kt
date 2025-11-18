package com.core.ui.navigation

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.core.ui.utility.MapOfTopAppBarStateSaver

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("NavController not found!")
}

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
    val currentEntry by currentBackStackEntryAsState()
    val currentRoute = currentEntry?.destination?.route
    val topAppBarStates = rememberSaveable(saver = MapOfTopAppBarStateSaver) {
        mutableStateMapOf()
    }

    val topAppBarState = remember(currentRoute) {
        if (currentRoute.isNullOrBlank()) {
            TopAppBarState(0f, 0f, 0f)
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

    return TopAppBarDefaults.pinnedScrollBehavior(state = topAppBarState)
}