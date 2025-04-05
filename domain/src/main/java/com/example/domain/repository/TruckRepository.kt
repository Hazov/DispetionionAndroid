package com.example.domain.repository

import com.example.domain.model.truck.create.CreateTruckResponse
import com.example.domain.model.truck.create.NewTruck
import com.example.domain.model.truck.registry.RegistryTruck
import com.example.domain.model.truck.details.TruckDetails

interface TruckRepository {
    suspend fun getTruck(id: Long): TruckDetails;
    suspend fun getTrucks(): List<RegistryTruck>;
    suspend fun createTruck(newTruck: NewTruck): CreateTruckResponse;

}