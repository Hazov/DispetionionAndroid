package com.example.domain.repository

import com.example.domain.model.auth.login.LoginResponse
import com.example.domain.model.auth.permissions.PermissionsResponse

interface AuthRepository {
    suspend fun login(login: String, password: String): LoginResponse
    suspend fun logout()
    suspend fun fetchPermissions(): PermissionsResponse
    suspend fun getPermissions(): Set<String>
}