package com.core.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
@Serializable
interface Screen {
    val showConfirmationOnLeave: Boolean
        get() = false

    @Composable
    fun getTitle(): String? {
        return null
    }

    @Composable
    fun getConfirmationTitle(): String? {
        return "Want to leave this page?"
    }

    @Composable
    fun getConfirmationMessage(): String? {
        return "Want to leave this page?"
    }
}