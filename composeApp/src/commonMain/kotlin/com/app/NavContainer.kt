package com.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.nav.buildScreenEntries
import com.app.nav.getCurrentScreen
import com.app.ui.TopBar
import com.app.ui.backPressHandler
import com.core.navigation.LocalCurrentScreen
import com.core.navigation.LocalNavController
import com.core.navigation.screen.ScreenProvider
import com.core.ui.CustomSnackbarHost
import com.core.ui.CustomSnackbarHostState
import com.feature.home.screen.HomeRoute

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NavContainer(
    snackbarHostState: CustomSnackbarHostState,
    screenProvider: ScreenProvider?
) {
    val navController = rememberNavController()
    val screenEntries = remember { buildScreenEntries(navController) }
    val currentScreen = getCurrentScreen(navController, screenEntries)
    val backPressHandler = backPressHandler(navController, currentScreen)

    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalCurrentScreen provides currentScreen
    ) {
        Scaffold(
            snackbarHost = {
                CustomSnackbarHost(state = snackbarHostState)
            },
            topBar = {
                TopBar(
                    screenProvider = screenProvider,
                    currentScreen = currentScreen,
                    onBackPress = backPressHandler
                )
            },
            floatingActionButton = {
                screenProvider?.fab?.let { fab ->
                    fab()
                }
            },
            content = { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = HomeRoute,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    screenEntries.forEach { screen ->
                        composable(
                            route = screen.route,
                            typeMap = screen.typeMap,
                            content = screen.content
                        )
                    }
                }

                BackHandler(onBack = backPressHandler)
            }
        )
    }
}