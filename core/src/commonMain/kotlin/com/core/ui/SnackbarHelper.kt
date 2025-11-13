package com.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier

val LocalSnackbarHostState = staticCompositionLocalOf<CustomSnackbarHostState> {
    error("SnackbarHostState not found!")
}

enum class SnackbarType {
    INFO, ERROR, SUCCESS
}

@Stable
class CustomSnackbarHostState {
    val hostState = SnackbarHostState()
    var currentType by mutableStateOf(SnackbarType.INFO)
        private set

    suspend fun showSnackbar(
        message: String,
        snackType: SnackbarType = SnackbarType.INFO,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration =
            if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    ): SnackbarResult {
        currentType = snackType

        return hostState.showSnackbar(
            message = message,
            actionLabel= actionLabel,
            withDismissAction = withDismissAction,
            duration = duration
        )
    }
}

@Composable
fun CustomSnackbarHost(
    state: CustomSnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(hostState = state.hostState, modifier = modifier) { data ->
        val (containerColor, contentColor) = when (state.currentType) {
            SnackbarType.INFO -> SnackbarDefaults.color to SnackbarDefaults.contentColor
            SnackbarType.SUCCESS -> MaterialTheme.colorScheme.tertiaryContainer to MaterialTheme.colorScheme.onTertiaryContainer
            SnackbarType.ERROR -> MaterialTheme.colorScheme.error to MaterialTheme.colorScheme.onError
        }

        Snackbar(
            snackbarData = data,
            containerColor = containerColor,
            contentColor = contentColor
        )
    }
}
