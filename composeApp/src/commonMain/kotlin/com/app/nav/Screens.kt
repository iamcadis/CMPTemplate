package com.app.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Print
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.toRoute
import com.core.extension.current
import com.core.navigation.screen.ScreenEntry
import com.core.ui.BaseScreen
import com.feature.home.screen.HomeRoute
import com.feature.home.screen.HomeScreen
import com.feature.home.screen.HomeViewModel
import com.feature.home.screen.TestRoute
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.LocalDateTime
import org.koin.compose.viewmodel.koinViewModel

fun buildScreenEntries(navController: NavController) = persistentListOf(
    ScreenEntry(route = HomeRoute::class) {
        HomeScreen(
            onNavigateToTestPage = {
                navController.navigate(route = TestRoute(data = "Hello World!"))
            }
        )
    },
    ScreenEntry(route = TestRoute::class) { entry ->
        BaseScreen(
            viewModel = koinViewModel<HomeViewModel>(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
            },
            topBarActions = {
                IconButton(
                    onClick = {
                        println("DATE TIME CLICKED: ${LocalDateTime.current().time}")
                    }
                ) {
                    Icon(imageVector = Icons.Default.Print, contentDescription = null)
                }
            }
        ) { _, _ ->
            Text(text = "Test: " + entry.toRoute<TestRoute>().data)
        }
    }
)