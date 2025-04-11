package com.example.dispidition.presentation.viewmodel.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.person.registry.RegistryPerson
import com.example.domain.usecase.person.GetPersonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonsRegistryViewModel@Inject constructor(private val getPersonsUseCase: GetPersonsUseCase): ViewModel() {
    private var _persons = MutableLiveData< List<RegistryPerson>>()
    var persons: LiveData<List<RegistryPerson>>  = _persons

    fun fetchPersons() {
        viewModelScope.launch {
            _persons.value = getPersonsUseCase.execute();
        }
    }
}