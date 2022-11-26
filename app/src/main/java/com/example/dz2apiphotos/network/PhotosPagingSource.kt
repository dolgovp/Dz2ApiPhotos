package com.example.dz2apiphotos.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dz2apiphotos.model.Photo
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PhotosPagingSource(
    private val service: ApiService,
    private val query: String
) : PagingSource<Int, Photo> (){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val photos = service.getPhotos(position,30)
            val nextKey = if (photos.isEmpty()){
                null
            } else {
                position + (params.loadSize / 30)
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
        return state.anchorPosition?.let {anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}