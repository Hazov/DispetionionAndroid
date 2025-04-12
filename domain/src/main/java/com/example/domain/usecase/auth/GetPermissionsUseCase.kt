package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository

class GetPermissionsUseCase (private val authRepository: AuthRepository) {
    suspend fun execute(): Set<String> {
        return authRepository.getPermissions()
    }
}