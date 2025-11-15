package com.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.core.navigation.screen.Screen

@Suppress("ParamsComparedByRef")
@Composable
fun backPressHandler(
    navController: NavController,
    currentScreen: Screen?
): () -> Unit {

    var showConfirmationSheet by rememberSaveable { mutableStateOf(false) }

    val onBackPress: () -> Unit = {
        if (currentScreen?.showConfirmationOnLeave == true) {
            showConfirmationSheet = true
        } else {
            navController.navigateUp()
        }
    }

    val cancelLeavingPage: () -> Unit = {
        showConfirmationSheet = false
    }

    currentScreen?.let { screen ->
        ConfirmationLeavePage(
            show = showConfirmationSheet,
            screen = screen,
            onCancel = cancelLeavingPage,
            onConfirm = {
                cancelLeavingPage()
                navController.navigateUp()
            }
        )
    }

    return onBackPress
}