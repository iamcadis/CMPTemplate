package com.core.network.config

import com.core.local.SecureStorage
import com.core.local.SecureStorage.Companion.clearToken
import com.core.local.SecureStorage.Companion.getAccessToken
import com.core.local.SecureStorage.Companion.getRefreshToken
import com.core.local.SecureStorage.Companion.storeAccessToken
import com.core.local.SecureStorage.Companion.storeRefreshToken
import com.core.network.ApiConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.providers.BearerAuthConfig
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

fun BearerAuthConfig.configureBearerToken(storage: SecureStorage) {
    loadTokens {
        storage.getAccessToken()?.let { token ->
            BearerTokens(accessToken = token, refreshToken = storage.getRefreshToken())
        }
    }

    refreshTokens {
        val oldRefreshToken = oldTokens?.refreshToken ?: return@refreshTokens null

        try {
            val response = client.post(urlString = ApiConfig.Url.REFRESH_TOKEN) {
                markAsRefreshTokenRequest() // Crucial: prevents infinite loops
                contentType(ContentType.Application.Json)
                setBody(mapOf("refreshToken" to oldRefreshToken))
            }.body<BearerTokens>()

            storage.storeAccessToken(response.accessToken)
            response.refreshToken?.let { token ->
                storage.storeRefreshToken(token)
            }

            response
        } catch (e: Exception) {
            e.printStackTrace()
            storage.clearToken()
            null
        }
    }
}