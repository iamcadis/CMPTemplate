package com.core.local.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.core.local.LocalStorage

internal class LocalStorageImpl(override val dataStore: DataStore<Preferences>) : LocalStorage