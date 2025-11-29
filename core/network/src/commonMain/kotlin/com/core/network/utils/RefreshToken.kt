package com.core.network.utils

import com.core.network.ApiConfig
import com.core.network.dto.AuthTokens
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.providers.BearerAuthConfig
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

fun BearerAuthConfig.requestRefreshToken(onRequested: suspend (AuthTokens) -> Unit) {
    refreshTokens {
        val oldRefreshToken = oldTokens?.refreshToken ?: return@refreshTokens null

        try {
            val response = client.post(urlString = ApiConfig.Url.REFRESH_TOKEN) {
                markAsRefreshTokenRequest() // Crucial: prevents infinite loops
                contentType(ContentType.Application.Json)
                setBody(mapOf("refreshToken" to oldRefreshToken))
            }.body<AuthTokens>()

            onRequested(response)
            BearerTokens(accessToken = response.accessToken, refreshToken = response.refreshToken)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}