package com.core.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.core.ui.data.MsgConfirmation
import com.core.ui.dialog.ConfirmationSheet
import com.core.ui.provider.ScreenProvider

@Composable
fun NavigationScaffold(
    screenProvider: ScreenProvider?,
    scrollBehavior: TopAppBarScrollBehavior,
    snackbarHostState: CustomSnackbarHostState,
    leavingMsgConfirmation: MsgConfirmation,
    canGoBack: Boolean,
    onGoBack: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {

    var showConfirmation by rememberSaveable { mutableStateOf(false) }
    val dismissConfirmation: () -> Unit = {
        showConfirmation = false
    }

    val backPressHandler: () -> Unit = {
        if (screenProvider?.confirmOnLeave == true) {
            showConfirmation = true
        } else {
            onGoBack()
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        snackbarHost = {
            CustomSnackbarHost(state = snackbarHostState)
        },
        topBar = {
            CustomTopAppBar(
                screenProvider = screenProvider,
                scrollBehavior = scrollBehavior,
                canGoBack = canGoBack,
                onBackPressed = backPressHandler
            )
        },
        floatingActionButton = {
            screenProvider?.floatingActionButton?.let { it() }
        },
        content = { paddingValues ->
            content(paddingValues)

            BackHandler(enabled = canGoBack, onBack = backPressHandler)

            if (showConfirmation) {
                ConfirmationSheet(
                    msgConfirmation = leavingMsgConfirmation,
                    onCancel = dismissConfirmation,
                    onConfirm = {
                        dismissConfirmation()
                        onGoBack()
                    }
                )
            }
        }
    )
}