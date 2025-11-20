package com.core.network

import com.core.local.SecureStorage
import com.core.network.config.configureBearerToken
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.api.SendingRequest
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object ApiClient {

    private val RemoveAuthorization = createClientPlugin("RemoveAuthorization") {
        on(SendingRequest) { request, _ ->
            if (request.headers[ApiConfig.SKIP_AUTH_HEADER] == "true") {
                request.headers.remove(HttpHeaders.Authorization)
            }
        }
    }

    fun get(storage: SecureStorage): HttpClient {
        return HttpClient {
            expectSuccess = true

            install(HttpTimeout) {
                connectTimeoutMillis = 10_000       // Time to connect to the server
                requestTimeoutMillis = 60_000       // Total max duration of the request
                socketTimeoutMillis = 30_000        // Time to send or receive chunks
            }

            install(ContentNegotiation) {
                json(json = Json {
                    isLenient = true
                    explicitNulls = false
                    encodeDefaults = true
                    ignoreUnknownKeys = true
                    allowStructuredMapKeys = true
                    allowSpecialFloatingPointValues = true
                })
            }

            install(Auth) {
                bearer {
                    configureBearerToken(storage)

                    sendWithoutRequest { request ->
                        val url = Url(urlString = ApiConfig.baseUrl)
                        request.url.host == url.host && request.url.protocol == url.protocol
                    }
                }
            }

            install(Logging) {
                level = if (isDebug) LogLevel.ALL else LogLevel.NONE
                logger = Logger.SIMPLE
            }

            install(RemoveAuthorization)

            defaultRequest {
                url(urlString = ApiConfig.baseUrl)
                header(key = HttpHeaders.ContentType, value = ContentType.Application.Json)
            }
        }
    }
}