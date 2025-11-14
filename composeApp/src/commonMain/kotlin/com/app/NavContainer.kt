package com.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.nav.TopBar
import com.app.nav.buildScreenEntries
import com.app.nav.currentScreenAsState
import com.core.navigation.LocalCurrentScreen
import com.core.navigation.LocalNavController
import com.core.ui.CustomSnackbarHost
import com.core.ui.CustomSnackbarHostState
import com.feature.home.screen.HomeRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavContainer(snackbarHostState: CustomSnackbarHostState) {
    val navController = rememberNavController()
    val screenEntries = remember { buildScreenEntries(navController) }
    val currentScreen by navController.currentScreenAsState(screenEntries)

    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalCurrentScreen provides currentScreen,
    ) {
        Scaffold(
            snackbarHost = {
                CustomSnackbarHost(state = snackbarHostState)
            },
            topBar = {
                TopBar(currentScreen = currentScreen)
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
            }
        )
    }
}