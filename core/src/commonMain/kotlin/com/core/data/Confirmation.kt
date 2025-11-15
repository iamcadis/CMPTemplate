package com.core.data

import androidx.compose.runtime.Immutable

@Immutable
data class Confirmation(
    val title: String,
    val message: String,
    val negativeLabel: String,
    val positiveLabel: String
)