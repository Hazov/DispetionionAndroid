package com.example.domain.repository

interface FirebaseRepository {
    suspend fun getDeviceToken(): String?
}