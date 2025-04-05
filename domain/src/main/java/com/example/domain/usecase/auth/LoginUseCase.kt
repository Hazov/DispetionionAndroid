package com.example.domain.usecase.auth

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.domain.repository.PersonRepository

class LoginUseCase(private val context: Context, private val personRepository: PersonRepository) {
    suspend fun execute(login: String, password: String){
        val response = personRepository.login(login, password)
        val pref = context.getSharedPreferences("person${response.personId}", MODE_PRIVATE)
        pref.edit().putString(response.token, null).apply()
    }
}