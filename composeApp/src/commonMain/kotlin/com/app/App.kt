package com.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.core.ui.LocalSnackbarHostState
import com.feature.home.di.homeModule
import org.koin.compose.KoinIsolatedContext
import org.koin.dsl.koinApplication

@Composable
fun App() {
    val snackbarHostState = remember { SnackbarHostState() }

    KoinIsolatedContext(context = koinApplication {
        modules(homeModule)
    }) {
        MaterialTheme {
            Scaffold(
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                },
                content = { paddingValues ->
                    CompositionLocalProvider(
                        value = LocalSnackbarHostState provides snackbarHostState,
                        content = {
                            NavHost(modifier = Modifier.padding(paddingValues))
                        }
                    )
                }
            )
        }
    }
}