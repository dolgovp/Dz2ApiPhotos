package com.example.dz2apiphotos.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dz2apiphotos.ui.theme.PAGE_LIMIT
import com.example.dz2apiphotos.ui.theme.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException



/*class PhotosPagingSource(
    private val networkPhotosRepository: PhotosRepository
) : PagingSource<Int, Photo> (){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val photos = networkPhotosRepository.getPhotos(position, PAGE_LIMIT)
            val nextKey = if (photos.isEmpty()){
                null
            } else {
                position + (params.loadSize / PAGE_LIMIT)
            }
            LoadResult.Page(
                data = photos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position -1,
                nextKey = nextKey
            )
        } catch (e: IOException){
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
        }
}*/
