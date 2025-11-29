package com.core.network

import com.core.network.utils.isDebug


object ApiConfig {
    val baseUrl = when(isDebug) {
        true -> "https://dummyjson.com/"
        false -> "https://api.real.com/"
    }

    internal object Url {
        const val REFRESH_TOKEN = "auth/refresh"
    }
}