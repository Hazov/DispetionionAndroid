package com.example.data.repo

import com.example.data.storage.server.ServerTruckStorage
import com.example.domain.model.truck.RegistryTruck
import com.example.domain.model.truck.TruckDetails
import com.example.domain.repository.TruckRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TruckRepositoryImpl : TruckRepository {

    override suspend fun GetTruck(id: Long): TruckDetails {
        val retro = Retrofit.Builder().baseUrl("http://169.254.191.65:8080").addConverterFactory(GsonConverterFactory.create()).build()
        val client = retro.create(ServerTruckStorage::class.java)
        return client.getTruck(id).toDomainTruckDetails()


//        return TruckDetails(
//            "Volvo", "XC90", "X 400 BH 777", "Хазов А.В.",
//            TruckDetailsTrip(
//                "Спб",
//                "Москва",
//                "Ильшат Сутулов",
//                "Приехал на разгрузку"
//            )
//        )
    }

    override suspend fun GetTrucks(): List<RegistryTruck> {
        val retrofit = Retrofit.Builder().baseUrl("http://169.254.191.65:8080").addConverterFactory(GsonConverterFactory.create()).build();
        val client = retrofit.create(ServerTruckStorage::class.java)
        return client.getTrucks().trucks.map { truck -> truck.toDomainRegistryTruck() }
    }
}
