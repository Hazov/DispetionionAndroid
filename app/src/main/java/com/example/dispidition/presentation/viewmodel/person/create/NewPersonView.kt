package com.example.dispidition.presentation.viewmodel.person.create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class NewPersonView {
    val firstName: MutableState<String>
    val lastName: MutableState<String>
    val middleName: MutableState<String>
    val email: MutableState<String>

    constructor() {
        firstName = mutableStateOf<String>("")
        lastName = mutableStateOf<String>("")
        middleName = mutableStateOf<String>("")
        email = mutableStateOf<String>("")
    }
}