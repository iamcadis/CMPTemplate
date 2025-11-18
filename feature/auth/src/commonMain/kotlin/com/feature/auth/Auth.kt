package com.feature.auth

import androidx.compose.runtime.Composable
import com.core.ui.BaseScreen
import com.feature.auth.screen.AuthContent
import com.feature.auth.screen.AuthEffect
import com.feature.auth.screen.AuthViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

object Auth {
    @Serializable
    object Route

    @Composable
    fun Screen(onNavigateToHomeRoute: () -> Unit) {
        BaseScreen(
            viewModel = koinViewModel<AuthViewModel>(),
            showTopAppBar = false,
            onEffect = { effect ->
                when(effect) {
                    AuthEffect.NavigateToHomeRoute -> onNavigateToHomeRoute()
                }
            },
            content = { state, dispatch ->
                AuthContent(state = state, dispatch = dispatch)
            }
        )
    }
}