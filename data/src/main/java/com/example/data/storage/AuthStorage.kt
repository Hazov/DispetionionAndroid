package com.example.data.storage

import com.example.data.model.auth.login.LoginRequest
import com.example.data.model.auth.login.LoginResponse
import com.example.data.model.auth.logout.LogoutResponse
import com.example.data.model.auth.permissions.PermissionsResponse

interface AuthStorage {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun logout(): LogoutResponse
    suspend fun getPermissions(): PermissionsResponse
}