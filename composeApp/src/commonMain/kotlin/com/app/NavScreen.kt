package com.app

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.home.Home

fun NavGraphBuilder.buildScreens(navController: NavController) {
    composable<Home.Route> {
        Home.Screen(
            onNavigateToTestPage = {

            }
        )
    }
}