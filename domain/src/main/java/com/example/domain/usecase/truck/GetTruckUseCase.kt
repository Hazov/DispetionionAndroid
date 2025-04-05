package com.example.domain.usecase.truck

import com.example.domain.model.truck.details.TruckDetails
import com.example.domain.repository.TruckRepository

class GetTruckUseCase(private val truckRepository: TruckRepository) {
    suspend fun execute(id: Long) : TruckDetails {
        return truckRepository.getTruck(id);
    }
}