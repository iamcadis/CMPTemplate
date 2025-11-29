package com.core.network

import com.core.network.observer.ConnectivityObserver
import com.core.network.utils.DispatcherProvider
import com.core.network.utils.NoContent
import com.core.network.utils.NoInternetException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiService(
    val httpClient: HttpClient,
    val dispatcher: DispatcherProvider,
    val connectivityObserver: ConnectivityObserver
) {

    inline fun <reified T> get(url: String) = request<T>(
        url = url,
        method = HttpMethod.Get
    )

    inline fun <reified T> post(url: String, data: Any?) = request<T>(
        url = url,
        method = HttpMethod.Post,
        data = data
    )

    inline fun <reified T> request(
        url: String,
        method: HttpMethod,
        data: Any? = null,
        contentType: ContentType = ContentType.Application.Json
    ) = flow {

        if (!connectivityObserver.isConnected()) {
            throw NoInternetException()
        }

        val response = httpClient.request(url) {
            this.method = method
            this.setBody(data)
            this.contentType(contentType)
        }

        if (response.status == HttpStatusCode.NoContent) {
            emit(NoContent as T)
        } else {
            emit(response.body<T>())
        }

    }.flowOn(dispatcher.io)
}