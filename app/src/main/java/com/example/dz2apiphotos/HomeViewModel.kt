package com.example.dz2apiphotos

import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.dz2apiphotos.model.Photo
import com.example.dz2apiphotos.network.PhotosPagingSource
import com.example.dz2apiphotos.network.PhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface PhotosUiState {
    data class Success(val photos: Flow<PagingData<Photo>>) : PhotosUiState
    object Error : PhotosUiState
    object Loading : PhotosUiState
}


class HomeViewModel(private val photosRepository: PhotosRepository) : ViewModel() {
    var photosUiState: PhotosUiState by mutableStateOf(PhotosUiState.Loading)
        private set

    init{
        getPhotos()
    }

    fun getPhotos(){
        photosUiState = PhotosUiState.Loading
        photosUiState = try{
            val photos : Flow<PagingData<Photo>> = Pager(
                pagingSourceFactory = { PhotosPagingSource(photosRepository)},
                config = PagingConfig(pageSize = 30)
            ).flow.cachedIn(viewModelScope)
            PhotosUiState.Success(photos)
        } catch (e: IOException){
            PhotosUiState.Loading
        }
    }

    /*fun getPhotos(){
        viewModelScope.launch{
            photosUiState = PhotosUiState.Loading
            Log.d(TAG,photosRepository.getPhotos().toString() )
            photosUiState = try {
                PhotosUiState.Success(photosRepository.getPhotos())
            } catch (e: IOException) {
                PhotosUiState.Error
            } catch (e: HttpException) {
                PhotosUiState.Error
            }
        }
    }*/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PhotosApplication)
                val photosRepository = application.container.photosRepository
                HomeViewModel(photosRepository = photosRepository)
            }
        }
    }
}