package com.core.network

expect val isDebug: Boolean

expect val localUrl: String

object ApiConfig {
    val baseUrl = when(isDebug) {
        true -> localUrl
        false -> "https://dummyjson.com"
    }

    internal const val SKIP_AUTH_HEADER = "X-Skip-Auth"

    internal object Url {
        const val REFRESH_TOKEN = "auth/refresh"
    }
}