package com.example.domain.usecase.trip.autocomplete

import com.example.domain.model.truck.autocomplete.TruckForAutoComplete
import com.example.domain.repository.TruckRepository

class GetTrucksAutoCompleteUseCase(private val truckRepository: TruckRepository) {
    suspend fun execute(): List<TruckForAutoComplete> {
        return truckRepository.getTrucksForAutoComplete();
    }
}