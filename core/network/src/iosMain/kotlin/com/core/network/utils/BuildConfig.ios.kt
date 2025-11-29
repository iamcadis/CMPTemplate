@file:OptIn(ExperimentalNativeApi::class)

package com.core.network.utils

import kotlin.experimental.ExperimentalNativeApi

actual val isDebug: Boolean
    get() = Platform.isDebugBinary