package com.example.domain.usecase.auth


import com.example.domain.repository.AuthRepository

class FetchPermissionsUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): Set<String> {
        return authRepository.fetchPermissions().permissions.toSet()
    }
}