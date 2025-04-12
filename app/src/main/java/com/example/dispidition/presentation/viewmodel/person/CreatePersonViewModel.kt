package com.example.dispidition.presentation.viewmodel.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispidition.presentation.viewmodel.person.create.NewPersonView
import com.example.domain.model.person.create.NewPerson
import com.example.domain.usecase.person.CreatePersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePersonViewModel @Inject constructor(private val createPersonUseCase: CreatePersonUseCase) :
    ViewModel() {


    var person = NewPersonView()

    fun createPerson() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        }
        val newPerson = NewPerson(
            person.firstName.value,
            person.lastName.value,
            person.middleName.value,
            person.email.value,

            )
        viewModelScope.launch(exceptionHandler) {
            createPersonUseCase.execute(newPerson)
        }

    }
}