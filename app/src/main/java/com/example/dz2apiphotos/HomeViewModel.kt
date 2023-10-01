package com.example.dz2apiphotos

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
import com.example.dz2apiphotos.model.Item
import com.example.dz2apiphotos.model.ShoppingList
import com.example.dz2apiphotos.network.ShoppingRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

private const val TAG = "SHoppingList"
sealed interface ShoppingUiState {
    data class Success(val shoppingList: ShoppingList) : ShoppingUiState
    data class Error(val error: Throwable) : ShoppingUiState
    object Loading : ShoppingUiState
}
class HomeViewModel(private val shoppingRepository: ShoppingRepository) : ViewModel() {

    var shoppingUiState: ShoppingUiState by mutableStateOf(ShoppingUiState.Loading)
        private set
    private var authKey: String = ""
    init{
        viewModelScope.launch {
            authKey = shoppingRepository.createTestKey()
            shoppingRepository.authenticate(authKey)
        }
    }
    fun getShoppingList(list_id: Int){
        viewModelScope.launch{
            shoppingUiState = ShoppingUiState.Loading
            shoppingUiState = try {
                ShoppingUiState.Success(shoppingRepository.getShoppingList(list_id))
            } catch (e: IOException) {
                ShoppingUiState.Error(e)
            } catch (e: HttpException) {
                ShoppingUiState.Error(e)
            }
        }
    }

    fun createShoppingList(key: String = authKey, name: String){

    }
    fun removeShoppingList(listId: Int){

    }
    fun addToShoppingList(id: Int, name: String, value: Int){

    }
    fun crossItOff(){

    }
    fun getAllMyShopLists(key: String = authKey){

    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PhotosApplication)
                val shoppingRepository = application.container.shoppingRepository
                HomeViewModel(shoppingRepository = shoppingRepository)
            }
        }
    }
}
