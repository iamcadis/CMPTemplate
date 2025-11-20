package com.core.network

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual val isDebug: Boolean
    get() = Platform.isDebugBinary

actual val localUrl: String
    get() = "http://localhost"