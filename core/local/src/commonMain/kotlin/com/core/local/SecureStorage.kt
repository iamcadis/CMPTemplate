package com.core.local

interface SecureStorage {
    suspend fun get(key: String) : String?
    suspend fun set(key: String, value: String) : Boolean
    suspend fun remove(key: String) : Boolean
}