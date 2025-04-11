package com.example.data.model.person.autocomplete

import kotlin.Long
import com.example.domain.model.person.autocomplete.DriverForAutoComplete

class DriverForAutoComplete(val id: Long, val firstName: String, val lastName: String) {
    fun toDomainDriver(): DriverForAutoComplete {
        return DriverForAutoComplete(id, firstName, lastName)
    }
}