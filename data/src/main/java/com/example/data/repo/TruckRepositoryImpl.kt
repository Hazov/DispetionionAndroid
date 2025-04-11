package com.example.data.repo


import com.example.data.model.truck.create.CreateTruckRequest
import com.example.data.storage.TruckStorage
import com.example.domain.model.truck.autocomplete.TruckForAutoComplete
import com.example.domain.model.truck.create.CreateTruckResponse
import com.example.domain.model.truck.create.NewTruck
import com.example.domain.model.truck.registry.RegistryTruck
import com.example.domain.model.truck.details.TruckDetails
import com.example.domain.repository.TruckRepository


class TruckRepositoryImpl(private val truckStorage: TruckStorage) : TruckRepository {

    override suspend fun getTruck(id: Long): TruckDetails {
        return truckStorage.getTruck(id).toDomainTruckDetails()
    }

    override suspend fun getTrucks(): List<RegistryTruck> {
        val body = truckStorage.getTrucks()
        return body.trucks.map { truck -> truck.toDomainRegistryTruck() }
    }

    override suspend fun createTruck(newTruck: NewTruck): CreateTruckResponse {

        val req  = CreateTruckRequest(newTruck.brand, newTruck.model, newTruck.roadNumber)
        var dataResponse = truckStorage.createTruck(req)
        return dataResponse.toDomainCreateTruckResponse()
    }

    override suspend fun getTrucksForAutoComplete(): List<TruckForAutoComplete>{
        val body = truckStorage.getTrucksForAutoComplete()
        return body.trucks.map { truck -> truck.toDomainTrucks() }
    }
}
