package com.yoesuv.composenetworking.networks

import com.yoesuv.composenetworking.models.ApiResponse
import com.yoesuv.composenetworking.models.GalleryModel
import com.yoesuv.composenetworking.models.PlaceModel
import io.ktor.client.call.body

class AppRepositoryImpl : AppRepository {

    private val apiService = ApiService(httpClientAndroid)

    override suspend fun getListPlace(): Result<ApiResponse<PlaceModel>> =
        kotlin.runCatching {
            apiService.getListPlaces().body<ApiResponse<PlaceModel>>()
        }

    override suspend fun getGallery(): Result<ApiResponse<GalleryModel>> =
        kotlin.runCatching {
            apiService.getGallery().body<ApiResponse<GalleryModel>>()
        }

}