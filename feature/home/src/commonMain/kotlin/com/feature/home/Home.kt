package com.feature.home

import androidx.compose.runtime.Composable
import com.core.ui.BaseScreen
import com.feature.home.screen.HomeAction
import com.feature.home.screen.HomeContent
import com.feature.home.screen.HomeEffect
import com.feature.home.screen.HomeViewModel
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import template.feature.home.generated.resources.Res
import template.feature.home.generated.resources.title

object Home {
    @Serializable
    object Route

    @Composable
    fun Screen(onNavigateToTestPage: () -> Unit) {
        BaseScreen(
            viewModel = koinViewModel<HomeViewModel>(),
            pageTitle = stringResource(Res.string.title),
            onEffect = { effect ->
                when(effect) {
                    HomeEffect.NavigateToTestRoute -> onNavigateToTestPage()
                }
            }
        ) { state, dispatch ->
            HomeContent(
                state = state,
                onOpenTestPage = { dispatch(HomeAction.OpenTestPage) },
                onTestError = { dispatch(HomeAction.TestShowError) },
                onTestLoading = { dispatch(HomeAction.ToggleLoading) },
            )
        }
    }
}