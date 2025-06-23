package com.example.domain.usecase.auth


import com.example.domain.repository.FirebaseRepository

class GetDeviceTokenUseCase(private val firebaseRepository: FirebaseRepository) {
    suspend fun execute(): String? {
        return firebaseRepository.getDeviceToken()
    }
}