package com.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.core.navigation.LocalNavController
import com.core.navigation.screen.Screen
import com.core.navigation.screen.ScreenProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    screenProvider: ScreenProvider?,
    currentScreen: Screen?,
    onBackPress: () -> Unit
) {

    val navController = LocalNavController.current
    val hasPreviousScreen = navController.previousBackStackEntry != null

    TopAppBar(
        title = {
            Text(text = currentScreen?.getTitle().orEmpty())
        },
        navigationIcon = {
            if (hasPreviousScreen) {
                IconButton(onClick = onBackPress) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        },
        actions = {
            screenProvider?.topBarActions?.let { actions ->
                actions()
            }
        }
    )
}