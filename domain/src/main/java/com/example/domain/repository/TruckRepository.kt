package com.example.domain.repository

import com.example.domain.model.truck.RegistryTruck
import com.example.domain.model.truck.TruckDetails

interface TruckRepository {
    suspend fun GetTruck(id: Long): TruckDetails;
    suspend fun GetTrucks(): List<RegistryTruck>;
}