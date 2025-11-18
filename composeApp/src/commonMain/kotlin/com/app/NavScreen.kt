package com.app

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.feature.home.Home
import com.feature.home.Test

fun NavGraphBuilder.buildScreens(navController: NavController) {
    composable<Home.Route> {
        Home.Screen(
            onNavigateToTestPage = {
                navController.navigate(Test.Route(data = "Hello world!"))
            }
        )
    }
    composable<Test.Route> {
        Test.Screen(data = it.toRoute<Test.Route>().data)
    }
}