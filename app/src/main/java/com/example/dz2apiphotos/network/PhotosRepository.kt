package com.example.dz2apiphotos.network

import com.example.dz2apiphotos.model.Photo

interface PhotosRepository {
    suspend fun getPhotos(): List<Photo>
}

class NetworkPhotosRepository(
    private val apiService: ApiService
) : PhotosRepository {
    override suspend fun getPhotos(): List<Photo> = apiService.getPhotos()
}

