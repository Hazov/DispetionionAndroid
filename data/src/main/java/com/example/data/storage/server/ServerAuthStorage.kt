package com.example.data.storage.server

import com.example.data.model.auth.login.LoginRequest
import com.example.data.model.auth.login.LoginResponse
import com.example.data.model.auth.logout.LogoutResponse
import com.example.data.model.auth.permissions.PermissionsResponse
import com.example.data.storage.AuthStorage
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServerAuthStorage : AuthStorage {
    @POST("/api/v1/auth/login")
    override suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("/api/v1/auth/logout")
    override suspend fun logout(): LogoutResponse

    @GET("/api/v1/auth/permissions")
    override suspend fun getPermissions(): PermissionsResponse
}