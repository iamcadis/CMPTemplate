package com.feature.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.core.extension.current
import com.core.navigation.Screen
import com.core.viewmodel.ViewAction
import com.core.viewmodel.ViewEffect
import com.core.viewmodel.ViewState
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import template.feature.home.generated.resources.Res
import template.feature.home.generated.resources.testing
import template.feature.home.generated.resources.title

@Serializable
object HomeRoute : Screen {
    @Composable
    override fun getTitle(): String {
        return stringResource(Res.string.title)
    }
}

@Serializable
data class TestRoute(val data: String) : Screen {
    @Composable
    override fun getTitle(): String {
        return stringResource(Res.string.testing)
    }
}

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