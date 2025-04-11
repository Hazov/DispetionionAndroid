package com.example.dispidition.presentation.viewmodel.trip.create_trip


import com.example.dispidition.presentation.viewmodel.common.autocomplete.AutoCompletable

class CreateTripTruckView(val id: Long, val brand: String, val model: String, val roadNumber: String) : AutoCompletable {


    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as CreateTripTruckView

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun getText(): String {
        return "${brand} ${model} (${roadNumber})"
    }
}