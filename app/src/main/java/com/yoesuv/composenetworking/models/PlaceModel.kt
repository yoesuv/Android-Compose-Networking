package com.yoesuv.composenetworking.models

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PlaceModel(
    @SerialName("nama") val name: String?,
    @SerialName("lokasi") val location: String?,
    @SerialName("deskripsi") val description: String?,
    @SerialName("thumbnail")val thumbnail: String?,
    @SerialName("gambar") val image: String?
)