package com.yoesuv.composenetworking.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoesuv.composenetworking.models.GalleryModel
import com.yoesuv.composenetworking.networks.AppRepositoryImpl
import com.yoesuv.composenetworking.networks.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {

    private val repository = AppRepositoryImpl()
    private var _galleries: MutableStateFlow<List<GalleryModel>> = MutableStateFlow(emptyList())
    val galleries: StateFlow<List<GalleryModel>> = _galleries
    val loading = mutableStateOf(false)

    fun loadGallery() {
        viewModelScope.launch {
            loading.value = true
            when (val response = repository.getGallery()) {
                is NetworkResult.GenericError -> {
                    loading.value = false
                    _galleries.value = emptyList()
                }

                is NetworkResult.HttpError -> {
                    loading.value = false
                    _galleries.value = emptyList()
                }

                is NetworkResult.NetworkError -> {
                    loading.value = false
                    _galleries.value = emptyList()
                }

                is NetworkResult.ParseError -> {
                    loading.value = false
                    _galleries.value = emptyList()
                }

                is NetworkResult.Success -> {
                    loading.value = false
                    _galleries.value = response.data.data ?: emptyList()
                    response.data.data?.let {
                        _galleries.update { it }
                    }
                }
            }
        }
    }

}