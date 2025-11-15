package com.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.app.ui.CustomTopAppBar
import com.app.ui.LeaveConfirmation
import com.app.ui.getDefaultConfirmation
import com.core.LocalNavController
import com.core.navigation.ScreenProvider
import com.core.ui.CustomSnackbarHost
import com.core.ui.CustomSnackbarHostState
import com.feature.home.screen.HomeRoute

@Composable
fun NavHost(
    screenProvider: ScreenProvider?,
    snackbarHostState: CustomSnackbarHostState,
) {
    val navController = rememberNavController()
    var showConfirmation by rememberSaveable { mutableStateOf(false) }

    val dismissConfirmation: () -> Unit = {
        showConfirmation = false
    }

    val backPressHandler: () -> Unit = {
        if (screenProvider?.confirmOnLeave == true) {
            showConfirmation = true
        } else {
            navController.navigateUp()
        }
    }

    CompositionLocalProvider(value = LocalNavController provides navController) {
        Scaffold(
            snackbarHost = {
                CustomSnackbarHost(state = snackbarHostState)
            },
            topBar = {
                CustomTopAppBar(screenProvider = screenProvider, onBackPress = backPressHandler)
            },
            floatingActionButton = {
                screenProvider?.fab?.let { it() }
            },
            content = { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = HomeRoute,
                    modifier = Modifier.padding(paddingValues),
                    enterTransition = {
                        fadeIn(
                            animationSpec = tween(durationMillis = 300, easing = LinearEasing)
                        ) + slideIntoContainer(
                            animationSpec = tween(durationMillis = 300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start
                        )
                    },
                    exitTransition = {
                        fadeOut(
                            animationSpec = tween(durationMillis = 300, easing = LinearEasing)
                        ) + slideOutOfContainer(
                            animationSpec = tween(durationMillis = 300, easing = EaseOut),
                            towards = AnimatedContentTransitionScope.SlideDirection.End
                        )
                    },
                    popEnterTransition = { slideInHorizontally { it } },
                    popExitTransition = { slideOutHorizontally { -it } },
                    builder = { buildScreens(navController) }
                )

                BackHandler(onBack = backPressHandler)

                screenProvider?.let { provider ->
                    LeaveConfirmation(
                        show = showConfirmation,
                        confirmation = provider.confirmationData ?: getDefaultConfirmation(),
                        onCancel = dismissConfirmation,
                        onConfirm = {
                            dismissConfirmation()
                            navController.navigateUp()
                        }
                    )
                }
            }
        )
    }
}