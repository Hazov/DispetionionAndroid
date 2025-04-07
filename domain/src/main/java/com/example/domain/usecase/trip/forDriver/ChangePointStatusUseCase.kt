package com.example.domain.usecase.trip.forDriver

import com.example.domain.repository.TripRepository

class ChangePointStatusUseCase(private val tripRepository: TripRepository) {
    fun execute(id: Long){
//        return tripRepository.changePointStatus(id)
    }
}