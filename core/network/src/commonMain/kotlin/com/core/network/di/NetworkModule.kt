package com.core.network.di

import com.core.network.ApiClient
import org.koin.dsl.module

val networkModule = module {
    single { ApiClient.get(get()) }
}