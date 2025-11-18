package com.feature.auth.screen

import com.core.viewmodel.BaseViewModel

class AuthViewModel : BaseViewModel<AuthState, AuthAction, AuthEffect>(
    initialState = AuthState.Initial
) {

}