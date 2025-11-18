package com.app

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.auth.Auth
import com.feature.home.Home

fun NavGraphBuilder.buildScreens(navController: NavController) {
    composable<Home.Route> {
        Home.Screen(
            onNavigateToAuthRoute = {
                navController.navigate(Auth.Route) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
    }
    composable<Auth.Route> {
        Auth.Screen(
            onNavigateToHomeRoute = {
                navController.navigate(Home.Route) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
    }
}