package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetailsCargoCustomer

data class TripDetailsCargoCustomerR(
    val firstName: String,
    val lastName: String,
    val company: TripDetailsCargoCustomerCompanyR
){
    fun toDomainTripDetailsCargoCustomer(): TripDetailsCargoCustomer{
        return TripDetailsCargoCustomer(
            firstName,
            lastName,
            company.toDomainTripDetailsCargoCustomerCompany()
        )
    }
}