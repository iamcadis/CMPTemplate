package com.app.di

import com.core.local.di.localModule
import com.core.network.di.networkModule
import org.koin.dsl.module

val coreModules = module {
    includes(localModule)
    includes(networkModule)
}