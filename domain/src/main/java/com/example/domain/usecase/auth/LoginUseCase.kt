package com.example.domain.usecase.auth


import com.example.domain.model.auth.login.LoginResponse
import com.example.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(login: String, password: String): LoginResponse {
        val response = authRepository.login(login, password)
        return response
    }

}