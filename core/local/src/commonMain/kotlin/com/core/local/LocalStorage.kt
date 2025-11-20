package com.core.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface LocalStorage {
    val dataStore: DataStore<Preferences>
}