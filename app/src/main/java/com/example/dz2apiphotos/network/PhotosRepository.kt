package com.example.dz2apiphotos.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.dz2apiphotos.model.Photo
import kotlinx.coroutines.flow.Flow


interface PhotosRepository {
    suspend fun getPhotos(page: Int, limit: Int = 50): List<Photo>
}

class NetworkPhotosRepository(
    private val apiService: ApiService
) : PhotosRepository {
    override suspend fun getPhotos(page: Int, limit: Int): List<Photo> = apiService.getPhotos(page,limit)
}



/*class PagingPhotosRepository(
    private val apiService: ApiService
) {
    fun getPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PhotosPagingSource(apiService)}
        ).flow
    }

    companion object{
        const val NETWORK_PAGE_SIZE = 50
    }
}*/



