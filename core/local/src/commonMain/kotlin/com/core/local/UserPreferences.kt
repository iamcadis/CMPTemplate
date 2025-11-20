package com.core.local

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val userId: Flow<Int?>
    val userHasLogin: Flow<Boolean>
    suspend fun storeUserId(userId: Int)
}