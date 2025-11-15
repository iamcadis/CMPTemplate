package com.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.core.LocalNavController
import com.core.navigation.ScreenProvider

@Composable
fun NavController.customPinnedScrollBehavior(): TopAppBarScrollBehavior {
    val mapStates = remember { mutableStateMapOf<String, TopAppBarState>() }
    val backStackEntry by currentBackStackEntryAsState()

    val route = backStackEntry?.destination?.route.orEmpty()
    val topAppBarState = mapStates.getOrPut(route) {
        TopAppBarState(
            initialHeightOffsetLimit = -Float.MAX_VALUE,
            initialHeightOffset = 0f,
            initialContentOffset = 0f
        )
    }

    return TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
}

@Composable
fun CustomTopAppBar(
    screenProvider: ScreenProvider?,
    onBackPress: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val navController = LocalNavController.current
    val hasPreviousScreen = navController.previousBackStackEntry != null

    screenProvider?.let { provider ->
        if (provider.title == null && provider.topBarActions == null) return

        TopAppBar(
            title = {
                Text(text = provider.title.orEmpty())
            },
            navigationIcon = {
                if (hasPreviousScreen) {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            },
            actions = {
                provider.topBarActions?.let { actions ->
                    actions()
                }
            },
            scrollBehavior = scrollBehavior
        )
    }
}