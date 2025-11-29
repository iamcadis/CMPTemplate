package com.core.network.di

import com.core.network.IosConnectivityObserverObserver
import com.core.network.observer.ConnectivityObserver
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module
    get() = module {
        single<ConnectivityObserver> {
            IosConnectivityObserverObserver()
        }
        single<HttpClientEngine> {
            Darwin.create {
                configureRequest {
                    setAllowsCellularAccess(true)
                }
            }
        }
    }