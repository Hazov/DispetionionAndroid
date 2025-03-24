package com.example.dispidition.domain.usecase

import com.example.dispidition.domain.repository.TruckRepository
import com.example.dispidition.presentation.screens.truck.TruckDetails
import com.example.dispidition.presentation.screens.truck.TruckDetailsTrip

class GetTruckUseCase(private val truckRepository: TruckRepository) {
    fun execute() : TruckDetails {
        return TruckDetails(
            "Volvo", "XC90", "X 400 BH 777", "Хазов А.В.",
            TruckDetailsTrip("Спб", "Москва", "Ильшат Сутулов", "Приехал на разгрязку")
        )
    }
}