package com.feature.auth.di

import com.feature.auth.screen.AuthViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule: Module = module {
    viewModelOf(constructor = ::AuthViewModel)
}