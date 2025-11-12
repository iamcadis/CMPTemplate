package com.feature.home.screen

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.core.viewmodel.ViewAction
import com.core.viewmodel.ViewEffect
import com.core.viewmodel.ViewState
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Immutable
data class HomeState(val loading: Boolean, val showContent: Boolean) : ViewState {
    companion object {
        @Stable
        val Initial = HomeState(loading = false, showContent = false)
    }
}

sealed interface HomeAction : ViewAction {
    data object TestShowError: HomeAction
    data object ToggleContent: HomeAction
}

sealed interface HomeEffect : ViewEffect