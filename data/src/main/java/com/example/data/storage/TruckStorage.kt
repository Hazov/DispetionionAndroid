package com.example.data.storage

import com.example.data.model.TruckDetailsResponse
import com.example.data.model.TrucksRegistryResponse

interface TruckStorage {
     suspend fun getTruck(id: Long) : TruckDetailsResponse;
     suspend fun getTrucks(): TrucksRegistryResponse;
}