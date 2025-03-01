package com.yoesuv.composenetworking.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoesuv.composenetworking.models.PlaceModel
import com.yoesuv.composenetworking.networks.AppRepositoryImpl
import com.yoesuv.composenetworking.networks.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListPlaceViewModel : ViewModel() {

    private val repository = AppRepositoryImpl()
    private var _places: MutableStateFlow<List<PlaceModel>> = MutableStateFlow(emptyList())
    val places: StateFlow<List<PlaceModel>> = _places
    val loading = mutableStateOf(false)

    fun loadListPlace() {
        viewModelScope.launch {
            loading.value = true
            when (val response = repository.getListPlace()) {
                is NetworkResult.GenericError -> {
                    loading.value = false
                    _places.update { emptyList() }
                }

                is NetworkResult.HttpError -> {
                    loading.value = false
                    _places.update { emptyList() }
                }

                is NetworkResult.NetworkError -> {
                    loading.value = false
                    _places.update { emptyList() }
                }

                is NetworkResult.ParseError -> {
                    loading.value = false
                    _places.update { emptyList() }
                }

                is NetworkResult.Success -> {
                    loading.value = false
                    response.data.data?.let { places -> _places.update { places } }
                }
            }
        }
    }

}