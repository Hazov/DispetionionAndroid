package com.example.domain.usecase.truck

import com.example.domain.model.truck.create.CreateTruckResponse
import com.example.domain.model.truck.create.NewTruck
import com.example.domain.repository.TruckRepository

class CreateTruckUseCase(private val truckRepository: TruckRepository) {
    suspend fun execute(newTruck: NewTruck): CreateTruckResponse {

        return truckRepository.createTruck(newTruck);
    }
}