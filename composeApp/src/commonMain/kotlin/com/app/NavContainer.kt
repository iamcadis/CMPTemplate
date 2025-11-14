package com.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.nav.TopBar
import com.app.nav.buildScreenEntries
import com.app.nav.currentScreenAsState
import com.app.ui.ConfirmationLeavePage
import com.core.navigation.LocalCurrentScreen
import com.core.navigation.LocalNavController
import com.core.ui.CustomSnackbarHost
import com.core.ui.CustomSnackbarHostState
import com.feature.home.screen.HomeRoute

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NavContainer(snackbarHostState: CustomSnackbarHostState) {
    val navController = rememberNavController()
    val screenEntries = remember { buildScreenEntries(navController) }
    val currentScreen by navController.currentScreenAsState(screenEntries)

    var showConfirmationSheet by rememberSaveable { mutableStateOf(false) }

    val onBackPress: () -> Unit = {
        if (currentScreen?.showConfirmationOnLeave == true) {
            showConfirmationSheet = true
        } else {
            navController.navigateUp()
        }
    }

    val cancelLeavingPage: () -> Unit = {
        showConfirmationSheet = false
    }

    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalCurrentScreen provides currentScreen,
    ) {
        Scaffold(
            snackbarHost = {
                CustomSnackbarHost(state = snackbarHostState)
            },
            topBar = {
                TopBar(currentScreen = currentScreen, onBack = onBackPress)
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

                BackHandler(onBack = onBackPress)

                currentScreen?.let { screen ->
                    ConfirmationLeavePage(
                        show = showConfirmationSheet,
                        screen = screen,
                        onCancel = cancelLeavingPage,
                        onConfirm = {
                            cancelLeavingPage()
                            navController.navigateUp()
                        }
                    )
                }
            }
        )
    }
}