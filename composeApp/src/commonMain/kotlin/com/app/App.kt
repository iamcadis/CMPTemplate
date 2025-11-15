package com.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.core.navigation.screen.LocalScreenConfigProvider
import com.core.navigation.screen.ScreenConfigProvider
import com.core.navigation.screen.ScreenProvider
import com.core.ui.CustomSnackbarHostState
import com.core.ui.LocalSnackbarHostState
import com.feature.home.di.homeModule
import org.koin.compose.KoinIsolatedContext
import org.koin.dsl.koinApplication

@Composable
fun App() {
    val snackbarHostState = remember { CustomSnackbarHostState() }

    var screenProvider by remember { mutableStateOf<ScreenProvider?>(null) }
    val screenConfigProvider = object : ScreenConfigProvider {
        override fun setProvider(provider: ScreenProvider) {
            screenProvider = provider
        }
    }

    KoinIsolatedContext(context = koinApplication {
        modules(homeModule)
    }) {
        MaterialTheme {
            CompositionLocalProvider(
                LocalSnackbarHostState provides snackbarHostState,
                LocalScreenConfigProvider provides screenConfigProvider
            ) {
                Surface {
                    NavContainer(
                        snackbarHostState = snackbarHostState,
                        screenProvider = screenProvider
                    )
                }
            }
        }
    }
}