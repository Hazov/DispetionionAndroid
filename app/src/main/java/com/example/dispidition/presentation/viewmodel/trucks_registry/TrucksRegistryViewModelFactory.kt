package com.example.dispidition.presentation.viewmodel.trucks_registry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repo.TruckRepositoryImpl
import com.example.domain.usecase.truck.GetTrucksUseCase

class TrucksRegistryViewModelFactory : ViewModelProvider.Factory {
    val truckRepository by lazy(LazyThreadSafetyMode.NONE) { TruckRepositoryImpl() }
    val getTrucksUseCase by lazy(LazyThreadSafetyMode.NONE) { GetTrucksUseCase(truckRepository) }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrucksRegistryViewModel(
            getTrucksUseCase = getTrucksUseCase,
        ) as T
    }
}