package com.feature.home.screen

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.core.common.current
import com.core.viewmodel.ViewAction
import com.core.viewmodel.ViewEffect
import com.core.viewmodel.ViewState
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Immutable
data class HomeState(
    override val pageLoading: Boolean,
    val currentDate: LocalDateTime
) : ViewState {
    companion object {
        @Stable
        val Initial = HomeState(
            pageLoading = false,
            currentDate = LocalDateTime.current(TimeZone.UTC)
        )
    }
}

sealed interface HomeAction : ViewAction {
    data object OpenTestPage: HomeAction
    data object TestShowError: HomeAction
    data object ToggleLoading: HomeAction
}

sealed interface HomeEffect : ViewEffect {
    data object NavigateToTestRoute: HomeEffect
}