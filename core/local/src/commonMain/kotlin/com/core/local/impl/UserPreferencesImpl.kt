package com.core.local.impl

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.core.local.LocalStorage
import com.core.local.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class UserPreferencesImpl(private val storage: LocalStorage) : UserPreferences {

    companion object {
        private val USER_ID = intPreferencesKey("user_id")
        private val USER_HAS_LOGIN = booleanPreferencesKey("user_has_login")
    }

    override val userId: Flow<Int?>
        get() = storage.dataStore.data.map { it[USER_ID] }

    override val userHasLogin: Flow<Boolean>
        get() = storage.dataStore.data.map { it[USER_HAS_LOGIN] == true }

    override suspend fun storeUserId(userId: Int) {
        storage.dataStore.edit { preferences ->
            preferences[USER_ID] = userId
            preferences[USER_HAS_LOGIN] = userId > 0
        }
    }
}