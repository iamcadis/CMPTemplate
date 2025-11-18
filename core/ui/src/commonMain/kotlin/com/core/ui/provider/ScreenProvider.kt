package com.core.ui.provider

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import com.core.ui.data.MsgConfirmation

@Immutable
data class ScreenProvider(
    val pageTitle: String? = null,
    val showTopAppBar: Boolean = true,
    val confirmOnLeave: Boolean = false,
    val msgConfirmation: MsgConfirmation? = null,
    val topAppBarActions: @Composable (RowScope.() -> Unit)? = null,
    val floatingActionButton: @Composable (() -> Unit)? = null,
)

@Stable
interface ScreenConfigProvider {
    fun setProvider(provider: ScreenProvider)
}

val LocalScreenConfigProvider = compositionLocalOf<ScreenConfigProvider> {
    error("ScreenConfigProvider not found!")
}