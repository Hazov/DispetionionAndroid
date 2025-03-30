package com.example.data.repo


import com.example.data.storage.TruckStorage
import com.example.domain.model.truck.RegistryTruck
import com.example.domain.model.truck.TruckDetails
import com.example.domain.repository.TruckRepository


class TruckRepositoryImpl(private val truckStorage: TruckStorage) : TruckRepository {

    override suspend fun GetTruck(id: Long): TruckDetails {
        return truckStorage.getTruck(id).toDomainTruckDetails()
    }

    override suspend fun GetTrucks(): List<RegistryTruck> {
        return truckStorage.getTrucks().trucks.map { truck -> truck.toDomainRegistryTruck() }
    }
}
