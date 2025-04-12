package com.example.dispidition.presentation.viewmodel.truck

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.truck.registry.RegistryTruck
import com.example.domain.usecase.truck.GetTrucksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrucksRegistryViewModel@Inject constructor(private val getTrucksUseCase: GetTrucksUseCase): ViewModel() {
    private var _trucks = MutableLiveData< List<RegistryTruck>>()
    var trucks: LiveData<List<RegistryTruck>>  = _trucks

    fun fetchTrucks() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        }
        viewModelScope.launch(exceptionHandler) {
            _trucks.value = getTrucksUseCase.execute();
        }
    }
}