package com.example.dz2apiphotos.network

import com.example.dz2apiphotos.model.Photo
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}
