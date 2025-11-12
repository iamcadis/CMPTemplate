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
data class HomeState(val loading: Boolean) : ViewState {
    companion object {
        @Stable
        val Initial = HomeState(loading = false)
    }
}

sealed interface HomeAction : ViewAction

sealed interface HomeEffect : ViewEffect