package com.core.network.di

import com.core.network.AndroidConnectivityObserverObserver
import com.core.network.observer.ConnectivityObserver
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module
    get() = module {
        single<ConnectivityObserver> {
            AndroidConnectivityObserverObserver(context = androidContext())
        }
        single<HttpClientEngine> {
            OkHttp.create {
                config {
                    retryOnConnectionFailure(true)
                }
            }
        }
    }