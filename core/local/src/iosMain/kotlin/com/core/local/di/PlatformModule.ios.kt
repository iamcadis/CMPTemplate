package com.core.local.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.core.local.IOSSecureStorage
import com.core.local.LocalStorage
import com.core.local.SecureStorage
import com.core.local.impl.LocalStorageImpl
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

internal actual val platformModule: Module
    get() = module {
        single<SecureStorage> { IOSSecureStorage() }
        single<LocalStorage> {
            LocalStorageImpl(
                dataStore = PreferenceDataStoreFactory.createWithPath(
                    produceFile = {
                        val docDir = NSFileManager.defaultManager.URLForDirectory(
                            directory = NSDocumentDirectory,
                            inDomain = NSUserDomainMask,
                            appropriateForURL = null,
                            create = false,
                            error = null
                        )
                        (requireNotNull(docDir).path + "/local.preferences_pb").toPath()
                    }
                )
            )
        }
    }