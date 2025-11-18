package com.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.core.ui.provider.LocalScreenConfigProvider
import com.core.ui.provider.ScreenConfigProvider
import com.core.ui.provider.ScreenProvider
import com.design.system.AppTheme
import com.feature.auth.di.authModule
import com.feature.home.di.homeModule
import org.koin.compose.KoinIsolatedContext
import org.koin.dsl.koinApplication

@Composable
fun App() {
    var screenProvider by remember { mutableStateOf(ScreenProvider()) }
    val screenConfigProvider = object : ScreenConfigProvider {
        override fun setProvider(provider: ScreenProvider) {
            screenProvider = provider
        }
    }

    KoinIsolatedContext(context = koinApplication {
        modules(homeModule, authModule)
    }) {
        AppTheme {
            CompositionLocalProvider(
                value = LocalScreenConfigProvider provides screenConfigProvider
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavHost(screenProvider = screenProvider)
                }
            }
        }
    }
}