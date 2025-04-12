package com.example.data.model.auth.login

import com.example.domain.model.auth.login.LoginResponse

class LoginResponse(val token: String, val personId: Long) {
    fun toDomainResponse(): LoginResponse {
        return LoginResponse(token, personId)
    }
}