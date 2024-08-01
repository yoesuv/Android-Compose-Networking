package com.yoesuv.composenetworking.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.yoesuv.composenetworking.models.GalleryModel
import com.yoesuv.composenetworking.networks.AppRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GalleryViewModel : ViewModel() {

    private val repository = AppRepositoryImpl()
    private var _galleries: MutableStateFlow<List<GalleryModel>> = MutableStateFlow(emptyList())
    val galleries: StateFlow<List<GalleryModel>> = _galleries
    val loading = mutableStateOf(false)

    suspend fun loadGallery() {
        loading.value = true
        repository.getGallery().fold(
            onSuccess = { data ->
                loading.value = false
                data.data?.let { galleries ->
                    _galleries.value = galleries
                }
            },
            onFailure = { error ->
                loading.value = false
                _galleries.value = emptyList()
            }
        )
    }

}