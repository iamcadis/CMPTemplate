package com.core.ui.data

import androidx.compose.runtime.Immutable

@Immutable
data class MsgConfirmation(
    val title: String,
    val message: String,
    val negativeLabel: String,
    val positiveLabel: String
)
