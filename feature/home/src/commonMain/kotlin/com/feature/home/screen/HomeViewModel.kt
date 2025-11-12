package com.feature.home.screen

import com.core.viewmodel.BaseViewModel
import kotlinx.coroutines.delay

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
            HomeAction.ToggleLoading -> launchSafe {
                updateState { copy(pageLoading = true) }
                delay(1000)
                updateState { copy(pageLoading = false) }
            }
        }
    }
}