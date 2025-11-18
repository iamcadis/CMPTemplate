package com.app

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.home.screen.HomeRoute
import com.feature.home.screen.HomeScreen

fun NavGraphBuilder.buildScreens(navController: NavController) {
    composable<HomeRoute> {
        HomeScreen(
            onNavigateToTestPage = {

            }
        )
    }
}