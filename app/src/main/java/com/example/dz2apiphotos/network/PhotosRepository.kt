package com.example.dz2apiphotos.network

import com.example.dz2apiphotos.model.Photo

interface PhotosRepository {
    suspend fun getPhotos(page: Int, limit: Int = 50): List<Photo>
}

class NetworkPhotosRepository(
    private val apiService: ApiService
) : PhotosRepository {
    override suspend fun getPhotos(page: Int, limit: Int): List<Photo> = apiService.getPhotos(page,limit)
}

