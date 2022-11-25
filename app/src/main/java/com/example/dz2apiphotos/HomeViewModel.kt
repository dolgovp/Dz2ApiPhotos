package com.example.dz2apiphotos

import android.content.ContentValues.TAG
import android.util.AndroidRuntimeException
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dz2apiphotos.model.Photo
import com.example.dz2apiphotos.network.PhotosRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface PhotosUiState {
    data class Success(val photos: List<Photo>) : PhotosUiState
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
        viewModelScope.launch{
            photosUiState = PhotosUiState.Loading
            photosUiState = try {
                PhotosUiState.Success(photosRepository.getPhotos())
            } catch (e: IOException) {
                PhotosUiState.Error
            } catch (e: HttpException) {
                PhotosUiState.Error
            }
        }
    }

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