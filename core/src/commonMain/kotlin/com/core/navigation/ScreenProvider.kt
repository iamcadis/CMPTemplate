package com.core.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.core.data.Confirmation

@Immutable
data class ScreenProvider(
    val title: String? = null,
    val confirmOnLeave: Boolean = false,
    val confirmationData: Confirmation? = null,
    val fab: @Composable (() -> Unit)? = null,
    val topBarActions: @Composable (RowScope.() -> Unit)? = null,
)

@Stable
interface ScreenConfigProvider {
    fun setProvider(provider: ScreenProvider)
}