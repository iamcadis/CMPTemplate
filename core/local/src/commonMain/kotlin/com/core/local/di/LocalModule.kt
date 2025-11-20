package com.core.local.di

import com.core.local.UserPreferences
import com.core.local.impl.UserPreferencesImpl
import org.koin.dsl.module

val coreLocalModule = module {
    includes(platformModule)
    single<UserPreferences> { UserPreferencesImpl(get()) }
}