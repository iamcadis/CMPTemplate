package com.core.local.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.core.local.AndroidSecureStorage
import com.core.local.LocalStorage
import com.core.local.SecureStorage
import com.core.local.impl.LocalStorageImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module
    get() = module {
        single<SecureStorage> {
            AndroidSecureStorage(
                context = androidContext(),
                dataStore = PreferenceDataStoreFactory.create {
                    androidContext().preferencesDataStoreFile("secure.preferences_pb")
                }
            )
        }
        single<LocalStorage> {
            LocalStorageImpl(
                dataStore = PreferenceDataStoreFactory.create {
                    androidContext().preferencesDataStoreFile("local.preferences_pb")
                }
            )
        }
    }