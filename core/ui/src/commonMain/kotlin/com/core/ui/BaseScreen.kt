package com.core.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.ui.data.MsgConfirmation
import com.core.ui.dialog.LoadingDialog
import com.core.ui.provider.LocalScreenConfigProvider
import com.core.ui.provider.ScreenProvider
import com.core.viewmodel.BaseViewModel
import com.core.viewmodel.ViewAction
import com.core.viewmodel.ViewEffect
import com.core.viewmodel.ViewState


@Suppress("ParamsComparedByRef")
@Composable
fun <S : ViewState, A : ViewAction, E : ViewEffect> BaseScreen(
    viewModel: BaseViewModel<S, A, E>,
    pageTitle: String? = null,
    showTopAppBar: Boolean = true,
    confirmOnLeave: Boolean = false,
    loadingText: String = "Please wait...",
    leavingConfirmation: MsgConfirmation? = null,
    topAppBarActions: @Composable (RowScope.() -> Unit)? = null,
    floatingActionButton: @Composable (() -> Unit)? = null,
    onEffect: (effect: E) -> Unit = { },
    content: @Composable (state: S, dispatch: (A) -> Unit) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = LocalSnackbarHostState.current
    val screenConfigProvider = LocalScreenConfigProvider.current

    LaunchedEffect(pageTitle, showTopAppBar, confirmOnLeave) {
        screenConfigProvider.setProvider(provider = ScreenProvider(
            pageTitle = pageTitle,
            showTopAppBar = showTopAppBar,
            confirmOnLeave = confirmOnLeave,
            msgConfirmation = leavingConfirmation,
            topAppBarActions = topAppBarActions,
            floatingActionButton = floatingActionButton
        ))
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect(collector = onEffect)
    }

    LaunchedEffect(viewModel) {
        viewModel.error.collect {
            snackbarHostState.showSnackbar(
                message = it.message ?: "Unexpected error occurred, please try again later!",
                snackType = SnackbarType.ERROR
            )
        }
    }

    content(state, viewModel::handleAction)

    if (state.pageLoading) {
        LoadingDialog(text = loadingText)
    }
}