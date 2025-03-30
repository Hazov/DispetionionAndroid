package com.example.data.storage

import com.example.data.model.truck.details.TruckDetailsResponse
import com.example.data.model.truck.registry.TrucksRegistryResponse

interface TruckStorage {
     suspend fun getTruck(id: Long) : TruckDetailsResponse;
     suspend fun getTrucks(): TrucksRegistryResponse;
}