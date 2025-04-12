package com.example.dispidition.presentation.viewmodel.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.person.details.PersonDetails
import com.example.domain.usecase.person.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(private val getPersonUseCase: GetPersonUseCase) :
    ViewModel() {

    private var _person = MutableLiveData<PersonDetails>()
    var person: LiveData<PersonDetails> = _person

    fun fetchPerson(id: Long) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        }
        viewModelScope.launch(exceptionHandler) {
            _person.value = getPersonUseCase.execute(id)
        }
    }

}
