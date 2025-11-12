package com.core.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.onLoad(action: (Boolean) -> Unit): Flow<T> {
    return this.onStart { action(true) }.onCompletion { action(false) }
}