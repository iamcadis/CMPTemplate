package com.feature.auth.screen

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.core.viewmodel.ViewAction
import com.core.viewmodel.ViewEffect
import com.core.viewmodel.ViewState

@Immutable
data class AuthState(
    override val pageLoading: Boolean,
    val email: String,
    val password: String
) : ViewState {
    companion object {
        @Stable
        val Initial = AuthState(
            pageLoading = false,
            email = "",
            password = ""
        )
    }
}

sealed interface AuthAction : ViewAction {
    data object SignIn: AuthAction
}

sealed interface AuthEffect : ViewEffect {
    data object NavigateToHomeRoute: AuthEffect
}