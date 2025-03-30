package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetailsCargoCustomerCompany

data class TripDetailsCargoCustomerCompanyR(val name: String){
    fun toDomainTripDetailsCargoCustomerCompany():TripDetailsCargoCustomerCompany{
        return TripDetailsCargoCustomerCompany(name)
    }
}