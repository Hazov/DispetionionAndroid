package com.example.data.auth

import android.content.Context

class TokenManager (private val  context: Context) {
    private val TOKEN_KEY = "jwt_token"
    private val AUTH_PREFS = "auth"

    fun getToken(): String {
        return context.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE).getString(TOKEN_KEY, "")
            .toString()
    }

    fun saveToken(token: String) {
        context.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE).edit().putString(TOKEN_KEY, token).apply()

    }

    fun deleteToken() {
        context.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE).edit().remove(TOKEN_KEY).apply()
    }
}