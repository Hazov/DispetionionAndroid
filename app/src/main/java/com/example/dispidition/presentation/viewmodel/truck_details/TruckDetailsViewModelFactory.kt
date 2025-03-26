package com.example.dispidition.presentation.viewmodel.truck_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repo.TruckRepositoryImpl
import com.example.domain.usecase.truck.GetTruckUseCase

class TruckDetailsViewModelFactory : ViewModelProvider.Factory {

    val truckRepository by lazy(LazyThreadSafetyMode.NONE) { TruckRepositoryImpl() }
    val getTruckUseCase by lazy(LazyThreadSafetyMode.NONE) { GetTruckUseCase(truckRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TruckDetailsViewModel(
            getTruckUseCase = getTruckUseCase,
        ) as T
    }
}