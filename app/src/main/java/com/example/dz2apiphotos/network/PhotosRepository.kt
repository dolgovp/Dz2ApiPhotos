package com.example.dz2apiphotos.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.dz2apiphotos.model.Photo
import com.example.dz2apiphotos.ui.theme.PAGE_LIMIT
import kotlinx.coroutines.flow.Flow


interface PhotosRepository {
    suspend fun getPhotos(page: Int, limit: Int = PAGE_LIMIT): List<Photo>
}

class NetworkPhotosRepository(
    private val apiService: ApiService
) : PhotosRepository {
    override suspend fun getPhotos(page: Int, limit: Int): List<Photo> = apiService.getPhotos(page,limit)
}

