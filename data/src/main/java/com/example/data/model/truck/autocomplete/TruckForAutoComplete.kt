package com.example.data.model.truck.autocomplete
import com.example.domain.model.truck.autocomplete.TruckForAutoComplete

class TruckForAutoComplete(val id: Long, val brand: String, val model:String, val roadNumber: String){
    fun toDomainTrucks() : TruckForAutoComplete{
        return TruckForAutoComplete(id,brand,model,roadNumber)
    }
}