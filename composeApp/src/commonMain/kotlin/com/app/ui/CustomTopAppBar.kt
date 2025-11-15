package com.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.core.LocalNavController
import com.core.navigation.ScreenProvider

/**
 * A custom TopAppBar composable that adapts its content based on
 * the current screen's configuration.
 *
 * This app bar will only be displayed if the `screenProvider` provides either a title
 * or a set of top bar actions. It automatically shows a back button if there is a previous screen
 * in the navigation back stack.
 *
 * @param screenProvider The [ScreenProvider] for the current screen,
 * which supplies the title and actions for the app bar. If null,
 * or if it has no title or actions, the app bar will not be rendered.
 * @param onBackPress A lambda function to be invoked when the back navigation icon is pressed.
 * @param scrollBehavior A [TopAppBarScrollBehavior] that defines how the top app bar
 * interacts with scrolling content.
 */
@Composable
fun CustomTopAppBar(
    screenProvider: ScreenProvider?,
    onBackPress: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
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
            },
            scrollBehavior = scrollBehavior
        )
    }
}