package com.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.core.ui.CustomSnackbarHostState
import com.core.ui.LocalSnackbarHostState
import com.core.ui.NavigationScaffold
import com.core.ui.data.MsgConfirmation
import com.core.ui.navigation.LocalNavController
import com.core.ui.navigation.enterTransition
import com.core.ui.navigation.exitTransition
import com.core.ui.navigation.rememberPinnedScrollBehavior
import com.core.ui.provider.ScreenProvider
import com.feature.home.Home
import org.jetbrains.compose.resources.stringResource
import template.composeapp.generated.resources.Res
import template.composeapp.generated.resources.leave_page_message
import template.composeapp.generated.resources.leave_page_title
import template.composeapp.generated.resources.stay_here
import template.composeapp.generated.resources.yes_leave

@Composable
fun NavHost(screenProvider: ScreenProvider?) {
    val navController = rememberNavController()
    val scrollBehavior = navController.rememberPinnedScrollBehavior()
    val msgConfirmation = screenProvider?.msgConfirmation ?: MsgConfirmation(
        title = stringResource(Res.string.leave_page_title),
        message = stringResource(Res.string.leave_page_message),
        negativeLabel = stringResource(Res.string.stay_here),
        positiveLabel = stringResource(Res.string.yes_leave)
    )

    val snackbarHostState = remember { CustomSnackbarHostState() }

    CompositionLocalProvider(
        LocalSnackbarHostState provides snackbarHostState,
        LocalNavController provides navController
    ) {
        NavigationScaffold(
            screenProvider = screenProvider,
            scrollBehavior = scrollBehavior,
            snackbarHostState = snackbarHostState,
            leavingMsgConfirmation = msgConfirmation,
            canGoBack = navController.previousBackStackEntry != null,
            onGoBack = { navController.navigateUp() }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Home.Route,
                modifier = Modifier.padding(paddingValues),
                enterTransition = {
                    enterTransition(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )
                },
                exitTransition = {
                    exitTransition(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )
                },
                popEnterTransition = {
                    enterTransition(
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                },
                popExitTransition = {
                    exitTransition(
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                },
                builder = { buildScreens(navController) }
            )
        }
    }
}