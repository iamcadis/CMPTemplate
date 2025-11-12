package com.feature.home.screen

import com.core.viewmodel.BaseViewModel

class HomeViewModel : BaseViewModel<HomeState, HomeAction, HomeEffect>(
    initialState = HomeState.Initial
) {
    override fun handleAction(action: HomeAction) {
        when(action) {
            HomeAction.TestShowError -> sendError(
                error = Exception("Test Error")
            )
            HomeAction.ToggleContent -> updateState {
                copy(showContent = !showContent)
            }
        }
    }
}