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
    fun getTitle(): String {
        return ""
    }

    @Composable
    fun getConfirmationTitle(): String? {
        return null
    }

    @Composable
    fun getConfirmationMessage(): String? {
        return null
    }

    @Composable
    fun cancelLabel(): String? {
        return null
    }

    @Composable
    fun confirmLabel(): String? {
        return null
    }
}