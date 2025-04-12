package com.example.data.auth

import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(val tokenManager: TokenManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer ${tokenManager.getToken()}")
        return chain.proceed(request.build())
    }
}