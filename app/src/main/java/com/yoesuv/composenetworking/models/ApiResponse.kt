package com.yoesuv.composenetworking.models

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ApiResponse<T>(
    @SerialName("status_code") val statusCode: Int?,
    @SerialName("data") val data: List<T>?
)