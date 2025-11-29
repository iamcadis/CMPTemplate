package com.core.network.utils

import com.core.network.BuildConfig

actual val isDebug: Boolean
    get() = BuildConfig.DEBUG