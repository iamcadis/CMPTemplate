package com.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.navigation.screen.LocalScreenConfigProvider
import com.core.navigation.screen.ScreenProvider
import com.core.viewmodel.BaseViewModel
import com.core.viewmodel.ViewAction
import com.core.viewmodel.ViewEffect
import com.core.viewmodel.ViewState

/**
 * A generic screen container that handles common UI states like loading and errors,
 * based on a single ViewState stream from a BaseViewModel.
 *
 * @param S The screen's state type, which must include loading and error properties.
 * @param A The screen's action/intent type.
 * @param E The screen's one-time effect type.
 * @param viewModel The instance of the BaseViewModel for this screen.
 * @param onEffect A callback to handle one-time side effects (e.g., navigation, toast).
 * @param content The main UI content to display when the state is stable.
 *                It receives the current state and a function to dispatch actions.
 */
@Suppress("ParamsComparedByRef")
@Composable
fun <S : ViewState, A : ViewAction, E : ViewEffect> BaseScreen(
    viewModel: BaseViewModel<S, A, E>,
    pageLoadingText: String? = null,
    onEffect: (E) -> Unit = {},
    floatingActionButton: @Composable (() -> Unit)? = null,
    content: @Composable (state: S, dispatch: (A) -> Unit) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = LocalSnackbarHostState.current
    val screenConfigProvider = LocalScreenConfigProvider.current

    LaunchedEffect(Unit) {
        screenConfigProvider.setProvider(
            provider = ScreenProvider(fab = floatingActionButton)
        )
    }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect(collector = onEffect)
    }

    LaunchedEffect(Unit) {
        viewModel.error.collect {
            snackbarHostState.showSnackbar(
                message = it.message ?: "An error occurred",
                snackType = SnackbarType.ERROR
            )
        }
    }

    content(state, viewModel::handleAction)

    if (state.pageLoading) {
        Dialog(onDismissRequest = {}) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CircularProgressIndicator(color = Color.White)
                Text(
                    text = pageLoadingText ?: "Please wait...",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}