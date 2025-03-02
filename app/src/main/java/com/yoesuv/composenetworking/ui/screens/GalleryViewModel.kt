package com.yoesuv.composenetworking.ui.screens

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
    private var _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private var _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing


    fun loadGallery() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val response = repository.getGallery()) {
                is NetworkResult.GenericError -> {
                    stopLoading()
                }

                is NetworkResult.HttpError -> {
                    stopLoading()
                }

                is NetworkResult.NetworkError -> {
                    stopLoading()
                }

                is NetworkResult.ParseError -> {
                    stopLoading()
                }

                is NetworkResult.Success -> {
                    stopLoading()
                    _galleries.value = response.data.data ?: emptyList()
                }
            }
        }
    }

    fun refreshGallery() {
        _isLoading.value = true
        viewModelScope.launch {
            _isRefreshing.update { true }
            _galleries.update { emptyList() }
            loadGallery()
        }
    }

    private fun stopLoading() {
        _isLoading.value = false
        _isRefreshing.value = false
        _galleries.update { emptyList() }
    }

}