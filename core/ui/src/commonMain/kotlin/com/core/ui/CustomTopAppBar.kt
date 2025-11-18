package com.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.core.ui.provider.ScreenProvider

@Composable
fun CustomTopAppBar(
    screenProvider: ScreenProvider?,
    scrollBehavior: TopAppBarScrollBehavior,
    canGoBack: Boolean,
    onBackPressed: () -> Unit,
) {
    screenProvider?.let { provider ->
        if (!provider.showTopAppBar) return

        TopAppBar(
            title = {
                Text(text = provider.pageTitle.orEmpty())
            },
            navigationIcon = {
                if (canGoBack) {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            },
            actions = {
                provider.topAppBarActions?.let { it() }
            },
            scrollBehavior = scrollBehavior
        )
    }
}