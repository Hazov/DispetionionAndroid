package com.example.data.storage

import com.example.data.model.truck.TruckDetailsResponse
import com.example.data.model.truck.TrucksRegistryResponse

interface TruckStorage {
     suspend fun getTruck(id: Long) : TruckDetailsResponse;
     suspend fun getTrucks(): TrucksRegistryResponse;
}