package com.core.network.utils

import com.core.network.dto.ApiError
import kotlinx.serialization.Serializable

@Serializable
object NoContent

class NoInternetException : Exception("No internet connection")
class ApiException(apiError: ApiError) : Exception("Error: ${apiError.message}")
