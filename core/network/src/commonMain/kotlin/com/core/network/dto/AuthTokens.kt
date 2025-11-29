package com.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthTokens(val accessToken: String, val refreshToken: String)
