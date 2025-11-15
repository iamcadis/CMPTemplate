package com.app

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.feature.home.screen.HomeRoute
import com.feature.home.screen.HomeScreen

fun NavGraphBuilder.buildScreens(navController: NavController) {
    composable<HomeRoute> {
        HomeScreen(
            onNavigateToTestPage = {
                navController.navigate(route = TestRoute(data = "Hello World!"))
            }
        )
    }
    composable<TestRoute> {
        TestScreen(data = it.toRoute<TestRoute>().data)
    }
}