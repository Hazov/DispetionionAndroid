package com.example.dispidition.presentation.viewmodel.person

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.person.create.NewPerson
import com.example.domain.model.truck.create.NewTruck
import com.example.domain.usecase.person.CreatePersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePersonViewModel @Inject constructor(private val createPersonUseCase: CreatePersonUseCase) :
    ViewModel() {
    var person = Person()

    fun newPerson() {
        person = Person()
    }

    fun createPerson() {
        val newPerson = NewPerson(
            person.firstName.value,
            person.lastName.value,
            person.middleName.value,
            person.email.value,

            )
        viewModelScope.launch {
            createPersonUseCase.execute(newPerson)
        }

    }
}

class Person {
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