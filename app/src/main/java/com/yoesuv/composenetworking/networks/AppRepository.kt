package com.yoesuv.composenetworking.networks

import com.yoesuv.composenetworking.models.ApiResponse
import com.yoesuv.composenetworking.models.GalleryModel
import com.yoesuv.composenetworking.models.PlaceModel

interface AppRepository {
    suspend fun getListPlace(): NetworkResult<ApiResponse<PlaceModel>>
    suspend fun getGallery(): NetworkResult<ApiResponse<GalleryModel>>
}