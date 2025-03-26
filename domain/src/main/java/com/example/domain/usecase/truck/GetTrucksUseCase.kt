package com.example.domain.usecase.truck

import com.example.domain.model.truck.RegistryTruck
import com.example.domain.repository.TruckRepository

class GetTrucksUseCase(private val truckRepository: TruckRepository) {
    suspend fun execute(): List<RegistryTruck> {
        return truckRepository.GetTrucks()
    }
}