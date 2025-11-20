package com.app.di

import com.feature.auth.di.authModule
import com.feature.home.di.homeModule
import org.koin.dsl.module

val featureModules = module {
    includes(authModule, homeModule)
}