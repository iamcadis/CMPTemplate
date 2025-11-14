package com.app.nav

import androidx.navigation.NavController
import com.core.ui.ScreenEntry
import com.feature.home.screen.HomeRoute
import com.feature.home.screen.HomeScreen
import kotlinx.collections.immutable.persistentListOf

fun buildScreenEntries(navController: NavController) = persistentListOf(
    ScreenEntry(HomeRoute::class) { HomeScreen() },
)