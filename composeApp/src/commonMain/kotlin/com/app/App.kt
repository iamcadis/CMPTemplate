package com.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.core.ui.CustomSnackbarHost
import com.core.ui.CustomSnackbarHostState
import com.core.ui.LocalSnackbarHostState
import com.feature.home.di.homeModule
import org.koin.compose.KoinIsolatedContext
import org.koin.dsl.koinApplication

@Composable
fun App() {
    val snackbarHostState = remember { CustomSnackbarHostState() }

    KoinIsolatedContext(context = koinApplication {
        modules(homeModule)
    }) {
        MaterialTheme {
            CompositionLocalProvider(value = LocalSnackbarHostState provides snackbarHostState) {
                Scaffold(
                    snackbarHost = {
                        CustomSnackbarHost(state = snackbarHostState)
                    },
                    content = {
                        NavHost(modifier = Modifier.padding(paddingValues = it))
                    }
                )
            }
        }
    }
}