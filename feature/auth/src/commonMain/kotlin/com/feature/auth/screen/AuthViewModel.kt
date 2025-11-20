package com.feature.auth.screen

import com.core.local.SecureStorage
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
                    delay(500)
                    secureStorage.set("access_token", "testing")
                    userPreferences.storeUserId(1)
                }
            }
        }
    }
}