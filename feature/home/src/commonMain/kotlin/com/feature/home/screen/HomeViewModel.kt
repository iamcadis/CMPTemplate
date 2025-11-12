package com.feature.home.screen

import androidx.lifecycle.SavedStateHandle
import com.core.viewmodel.BaseViewModel

class HomeViewModel(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeState, HomeAction, HomeEffect>(
    initialState = HomeState.Initial
) {
    override fun loadInitialData() {

    }

    override fun handleAction(action: HomeAction) {

    }
}