package com.feature.home.screen

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.core.ui.Screen
import com.core.viewmodel.ViewAction
import com.core.viewmodel.ViewEffect
import com.core.viewmodel.ViewState
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute : Screen {
    override val title: String = "Home"
}

@Immutable
data class HomeState(override val pageLoading: Boolean, val showContent: Boolean) : ViewState {
    companion object {
        @Stable
        val Initial = HomeState(pageLoading = false, showContent = false)
    }
}

sealed interface HomeAction : ViewAction {
    data object TestShowError: HomeAction
    data object ToggleContent: HomeAction
    data object ToggleLoading: HomeAction
}

sealed interface HomeEffect : ViewEffect