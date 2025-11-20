package com.core.network

actual val isDebug: Boolean
    get() = BuildConfig.DEBUG

actual val localUrl: String
    get() = "http://10.0.0.2"