package com.core.network.di

import com.core.network.ApiClient
import com.core.network.ApiService
import com.core.network.utils.DefaultDispatchers
import com.core.network.utils.DispatcherProvider
import com.core.network.utils.isDebug
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val platformModule: Module

val networkModule = module {
    includes(platformModule)
    single<DispatcherProvider> {
        DefaultDispatchers()
    }
    single<HttpClient> {
        ApiClient(engine = get(), storage = get(), isDebug = isDebug).client
    }
    single<ApiService> {
        ApiService(httpClient = get(), dispatcher = get(), connectivityObserver = get())
    }
}