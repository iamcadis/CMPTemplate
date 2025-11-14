package com.app.nav

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.toRoute
import com.core.navigation.screen.ScreenEntry
import com.feature.home.screen.HomeRoute
import com.feature.home.screen.HomeScreen
import com.feature.home.screen.TestRoute
import kotlinx.collections.immutable.persistentListOf

fun buildScreenEntries(navController: NavController) = persistentListOf(
    ScreenEntry(route = HomeRoute::class) {
        HomeScreen(
            onNavigateToTestPage = {
                navController.navigate(route = TestRoute(data = "Hello World!"))
            }
        )
    },
    ScreenEntry(route = TestRoute::class) { entry ->
        Text(text = entry.toRoute<TestRoute>().data)
    }
)