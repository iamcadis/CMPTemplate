package com.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.core.LocalNavController
import com.core.navigation.ScreenProvider

@Composable
fun CustomTopAppBar(
    screenProvider: ScreenProvider?,
    onBackPress: () -> Unit
) {

    val navController = LocalNavController.current
    val hasPreviousScreen = navController.previousBackStackEntry != null

    screenProvider?.let { provider ->
        if (provider.title == null && provider.topBarActions == null) return

        TopAppBar(
            title = {
                Text(text = provider.title.orEmpty())
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
                provider.topBarActions?.let { actions ->
                    actions()
                }
            }
        )
    }
}