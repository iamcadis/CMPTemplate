package com.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(val code: Int, val message: String)