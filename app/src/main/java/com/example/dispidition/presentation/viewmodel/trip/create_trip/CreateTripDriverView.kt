package com.example.dispidition.presentation.viewmodel.trip.create_trip

import com.example.ui.autocomplete.AutoCompletable

class CreateTripDriverView(val id: Long, val firstName: String, val lastName: String) : AutoCompletable{

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as CreateTripDriverView

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun getText(): String {
        return "${lastName} ${firstName}"
    }
}