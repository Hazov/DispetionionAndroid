package com.example.data.repo

import com.example.data.auth.PermissionsManager
import com.example.data.auth.TokenManager
import com.example.data.storage.AuthStorage
import com.example.data.model.auth.login.LoginRequest
import com.example.domain.model.auth.login.LoginResponse
import com.example.domain.model.auth.permissions.PermissionsResponse
import com.example.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authStorage: AuthStorage,
                         private val tokenManager: TokenManager,
                         private val permissionsManager: PermissionsManager)
    : AuthRepository {

    override suspend fun login(login: String, password: String): LoginResponse{
        val request = LoginRequest(login, password)
        val response = authStorage.login(request)
        tokenManager.saveToken(response.token)
        return response.toDomainResponse()
    }

    override suspend fun logout(){
        tokenManager.deleteToken()
        permissionsManager.deletePermissions()
    }

    override suspend fun fetchPermissions(): PermissionsResponse {
        val response = authStorage.getPermissions()
        permissionsManager.savePermissions(response.permissions)
        return response.toDomainPermissions()
    }

    override suspend fun getPermissions(): Set<String>{
        return permissionsManager.getPermissions()
    }
}