package com.app

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.di.coreModules
import com.app.di.featureModules
import com.core.local.UserPreferences
import com.core.ui.provider.LocalScreenConfigProvider
import com.core.ui.provider.ScreenConfigProvider
import com.core.ui.provider.ScreenProvider
import com.design.system.AppTheme
import kotlinx.coroutines.delay
import org.koin.compose.KoinMultiplatformApplication
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration

private const val SPLASH_TIME = 700

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {
    var screenProvider by remember { mutableStateOf(ScreenProvider()) }
    val screenConfigProvider = object : ScreenConfigProvider {
        override fun setProvider(provider: ScreenProvider) {
            screenProvider = provider
        }
    }

    var showSplashScreen by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(SPLASH_TIME.toLong())
        showSplashScreen = false
    }

    KoinMultiplatformApplication(config = koinConfiguration {
        modules(coreModules, featureModules)
    }) {
        val userPrefs = koinInject<UserPreferences>()
        val userHasLogin by userPrefs.userHasLogin.collectAsStateWithLifecycle(initialValue = false)
        AppTheme {
            CompositionLocalProvider(
                value = LocalScreenConfigProvider provides screenConfigProvider
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AnimatedContent(
                        label = "Content Transition",
                        targetState = showSplashScreen,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(SPLASH_TIME)) +
                                    scaleIn(initialScale = 0.92f) togetherWith
                                    fadeOut(animationSpec = tween(SPLASH_TIME))
                        }
                    ) { showSplash ->
                        if (showSplash) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        } else {
                            NavHost(userHasLogin = userHasLogin, screenProvider = screenProvider)
                        }
                    }
                }
            }
        }
    }
}