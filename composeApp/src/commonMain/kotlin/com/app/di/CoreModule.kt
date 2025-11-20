package com.app.di

import com.core.local.di.coreLocalModule
import org.koin.dsl.module

val coreModules = module {
    includes(coreLocalModule)
}