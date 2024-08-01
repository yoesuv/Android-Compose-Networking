package com.yoesuv.composenetworking.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoesuv.composenetworking.models.PlaceModel
import com.yoesuv.composenetworking.networks.AppRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListPlaceViewModel : ViewModel() {

    private val repository = AppRepositoryImpl()
    private var _places: MutableStateFlow<List<PlaceModel>> = MutableStateFlow(emptyList())
    val places: StateFlow<List<PlaceModel>> = _places
    val loading = mutableStateOf(false)

    suspend fun loadListPlace() {
        viewModelScope.launch {
            loading.value = true
            repository.getListPlace().fold(
                onSuccess = { data ->
                    loading.value = false
                    data.data?.let { places ->
                        _places.update { places }
                    }
                },
                onFailure = { error ->
                    Log.e("TAG_ERROR", "ListPlaceViewModel # ERROR $error")
                    loading.value = false
                    _places.update { emptyList() }
                }
            )
        }
    }

}