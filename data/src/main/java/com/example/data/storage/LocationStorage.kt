package com.example.data.storage

import com.example.domain.model.gps.Location
import retrofit2.http.Body

interface LocationStorage {
    suspend fun uploadLocation(location: Location)
}