package com.example.data.repo

import android.content.Context
import com.example.domain.repository.FirebaseRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl() : FirebaseRepository  {
    override suspend fun getDeviceToken(): String? {
        return FirebaseMessaging.getInstance().token.await()
    }
}