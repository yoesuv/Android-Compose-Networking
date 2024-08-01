package com.yoesuv.composenetworking.models

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class GalleryModel(
    val caption: String?,
    val thumbnail: String?,
    val image: String?,
)