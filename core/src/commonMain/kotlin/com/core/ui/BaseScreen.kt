package com.core.ui

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.viewmodel.BaseViewModel
import com.core.viewmodel.ViewAction
import com.core.viewmodel.ViewEffect
import com.core.viewmodel.ViewState
import kotlinx.coroutines.launch

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
@Composable
fun <S : ViewState, A : ViewAction, E : ViewEffect> BaseScreen(
    viewModel: BaseViewModel<S, A, E>,
    pageLoading: Boolean = false,
    loadingText: String? = null,
    onEffect: (E) -> Unit = {},
    content: @Composable (state: S, dispatch: (A) -> Unit) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle(initialValue = null)

    val scope = rememberCoroutineScope()
    val snackbarHostState = LocalSnackbarHostState.current

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect(collector = onEffect)
    }

    Surface {
        content(state, viewModel::handleAction)
        LoadingOverlay(show = pageLoading, text = loadingText)
    }

    error?.let { throwable ->
        when(throwable) {
            else -> {
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = error?.message ?: "An error occurred",
                        duration = SnackbarDuration.Long,
                        withDismissAction = true
                    )
                    when(result) {
                        SnackbarResult.ActionPerformed -> {
                            viewModel.retry()
                        }
                        SnackbarResult.Dismissed -> {
                            viewModel.clearError()
                        }
                    }
                }
            }
        }
    }
}

val LocalSnackbarHostState = staticCompositionLocalOf<SnackbarHostState> {
    error("SnackbarHostState not found!")
}