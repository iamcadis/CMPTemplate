package com.feature.home.di

import com.feature.home.screen.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule: Module = module {
    viewModelOf(constructor = ::HomeViewModel)
}