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
import com.example.dz2apiphotos.model.AllStatus
import com.example.dz2apiphotos.model.Item
import com.example.dz2apiphotos.model.ListStatus
import com.example.dz2apiphotos.model.ShoppingList
import com.example.dz2apiphotos.network.ShoppingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.reflect.typeOf

private const val TAG = "ShoppingList"
sealed interface ShoppingUiState {
    data class Success(val shoppingList: ShoppingList ) : ShoppingUiState
    data class Error(val error: Throwable) : ShoppingUiState
    object Loading : ShoppingUiState
}
sealed interface AllListsUiState {
    data class Success(val shoppingList: AllStatus, ) : AllListsUiState
    data class Error(val error: Throwable) : AllListsUiState
    object Loading : AllListsUiState
}

class HomeViewModel (private val shoppingRepository: ShoppingRepository) : ViewModel() {

    private val _currentListState = MutableStateFlow<ShoppingUiState>(ShoppingUiState.Loading)
    val currentListState: StateFlow<ShoppingUiState> = _currentListState

    private val _allListsState = MutableStateFlow<AllListsUiState>(AllListsUiState.Loading)
    val allListsState: StateFlow<AllListsUiState> = _allListsState
    val authKey = "Q14VD6"
    init{
        viewModelScope.launch {
            getAllMyShopLists(authKey)
        }
    }
    fun getAllMyShopLists(key: String){
        viewModelScope.launch{
            _allListsState.value = AllListsUiState.Loading
            _allListsState.value = try {
                Log.d(TAG, shoppingRepository.getAllMyShopLists(key).shop_list.toString() )
                AllListsUiState.Success(shoppingRepository.getAllMyShopLists(key))
            } catch (e: IOException) {
                AllListsUiState.Error(e)
            } catch (e: HttpException) {
                AllListsUiState.Error(e)
            }
        }
    }
    fun getShoppingList(list_id: Int){
        viewModelScope.launch{
            _currentListState.value = ShoppingUiState.Loading
            _currentListState.value = try {
                Log.d(TAG,shoppingRepository.getShoppingList(list_id).item_list.toString() )
                ShoppingUiState.Success(shoppingRepository.getShoppingList(list_id))
            } catch (e: IOException) {
                ShoppingUiState.Error(e)
            } catch (e: HttpException) {
                ShoppingUiState.Error(e)
            }
        }
    }

    fun createShoppingList(key: String, name: String){
        viewModelScope.launch {
            shoppingRepository.createShoppingList(key, name)
        }
        getAllMyShopLists(key)
    }
    fun removeShoppingList(list_id: Int){
        viewModelScope.launch {
            shoppingRepository.removeShoppingList(list_id)
        }
    }
    fun addToShoppingList(list_id: Int, name: String, value: Int){
        viewModelScope.launch {
            shoppingRepository.addToShoppingList(list_id,name,value)

        }
        getShoppingList(list_id)
    }
    fun crossItOff(item_id: Int){
        viewModelScope.launch {
            shoppingRepository.crossItOff(item_id)
        }
        // перенести обновление стейта
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
