package com.feature.auth.screen

import com.core.local.SecureStorage
import com.core.local.SecureStorage.Companion.storeAccessToken
import com.core.local.UserPreferences
import com.core.viewmodel.BaseViewModel
import kotlinx.coroutines.delay

class AuthViewModel(
    private val secureStorage: SecureStorage,
    private val userPreferences: UserPreferences
) : BaseViewModel<AuthState, AuthAction, AuthEffect>(
    initialState = AuthState.Initial
) {
    override fun handleAction(action: AuthAction) {
        when(action) {
            AuthAction.SignIn -> {
                launchSafe {
                    updateState { copy(pageLoading = true) }
                    delay(500)
                    updateState { copy(pageLoading = false) }

                    val isSuccess = secureStorage.storeAccessToken(token = "testing")
                    if (isSuccess) {
                        userPreferences.storeUserId(1)
                    }
                }
            }
        }
    }
}