package com.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
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
import com.core.LocalScreenConfigProvider
import com.core.LocalSnackbarHostState
import com.core.data.Confirmation
import com.core.navigation.ScreenProvider
import com.core.viewmodel.BaseViewModel
import com.core.viewmodel.ViewAction
import com.core.viewmodel.ViewEffect
import com.core.viewmodel.ViewState

/**
 * A generic screen composable that provides a base structure for screens in the application.
 *
 * This composable handles common screen-level concerns such as:
 * - Collecting and observing state, effects, and errors from a [BaseViewModel].
 * - Displaying a full-page loading indicator.
 * - Showing error messages in a snackbar.
 * - Configuring screen-specific properties like the title, top bar actions, and floating action button.
 *
 * @param S The type of the [ViewState] for this screen.
 * @param A The type of the [ViewAction] that can be dispatched from this screen.
 * @param E The type of the [ViewEffect] that can be emitted from the ViewModel.
 * @param viewModel The [BaseViewModel] instance for this screen.
 * @param pageTitle The title to be displayed in the top app bar.
 * @param showTopBar Whether to show the top app bar. Defaults to true.
 * @param loadingText The text to display below the loading indicator. Defaults to "Please wait...".
 * @param confirmOnLeave If true, a confirmation dialog will be shown when the user tries to navigate away from the screen.
 * @param onEffect A callback to handle side effects emitted by the ViewModel.
 * @param topBarActions A composable lambda to define actions for the top app bar.
 * @param floatingActionButton A composable lambda for the floating action button.
 * @param content The main content of the screen. It receives the current state and a lambda to dispatch actions.
 */
@Suppress("ParamsComparedByRef")
@Composable
fun <S : ViewState, A : ViewAction, E : ViewEffect> BaseScreen(
    viewModel: BaseViewModel<S, A, E>,
    pageTitle: String = "",
    showTopBar: Boolean = true,
    loadingText: String = "Please wait...",
    confirmOnLeave: Boolean = false,
    leaveConfirmation: Confirmation? = null,
    onEffect: (effect: E) -> Unit = {},
    topBarActions: @Composable (RowScope.() -> Unit)? = null,
    floatingActionButton: @Composable (() -> Unit)? = null,
    content: @Composable (state: S, dispatch: (A) -> Unit) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = LocalSnackbarHostState.current
    val screenConfigProvider = LocalScreenConfigProvider.current

    LaunchedEffect(Unit) {
        screenConfigProvider.setProvider(
            provider = ScreenProvider(
                title = if (showTopBar) pageTitle else null,
                confirmOnLeave = confirmOnLeave,
                confirmationData = leaveConfirmation,
                fab = floatingActionButton,
                topBarActions = topBarActions
            )
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
                    text = loadingText,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}