package com.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

inline fun <T> Flow<T>.onLoad(crossinline action: (Boolean) -> Unit): Flow<T> {
    return this.onStart { action(true) }.onCompletion { action(false) }
}