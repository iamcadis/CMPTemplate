package com.feature.home.screen

import com.core.viewmodel.BaseViewModel
import kotlinx.coroutines.delay

class HomeViewModel : BaseViewModel<HomeState, HomeAction, HomeEffect>(
    initialState = HomeState.Initial
) {
    override fun handleAction(action: HomeAction) {
        when(action) {
            HomeAction.OpenTestPage -> sendEffect(effect = HomeEffect.NavigateToTestRoute)
            HomeAction.TestShowError -> sendError(
                error = Exception("Test Error")
            )
            HomeAction.ToggleLoading -> launchSafe {
                updateState { copy(pageLoading = true) }
                delay(5000)
                updateState { copy(pageLoading = false) }
            }
        }
    }
}