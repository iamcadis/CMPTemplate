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
            HomeAction.ToggleLoading -> launchSafe {
                updateState { copy(pageLoading = true) }
                delay(5000)
                updateState { copy(pageLoading = false) }
            }
        }
    }

    override fun loadInitialData() {
//        launchSafe {
//            updateState { copy(pageLoading = true) }
//            delay(timeMillis = 1000)
////            updateState { copy(pageShimmer = false) }
//            updateState { copy(pageLoading = false) }
//            sendEffect(effect = HomeEffect.NavigateToAuthRoute)
//        }
    }
}