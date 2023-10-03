package com.example.dz2apiphotos.network

import com.example.dz2apiphotos.model.AllStatus
import com.example.dz2apiphotos.model.AuthStatus
import com.example.dz2apiphotos.model.CrossOffStatus
import com.example.dz2apiphotos.model.ItemStatus
import com.example.dz2apiphotos.model.ListStatus
import com.example.dz2apiphotos.model.RemoveStatus
import com.example.dz2apiphotos.model.ShoppingList



interface ShoppingRepository {
    //suspend fun getPhotos(page: Int, limit: Int = PAGE_LIMIT): List<Photo>
    suspend fun getShoppingList(list_id: Int): ShoppingList
    suspend fun getAllMyShopLists(key: String): AllStatus
    suspend fun crossItOff(id: Int): CrossOffStatus
    suspend fun addToShoppingList(id: Int, value: String, n: Int): ItemStatus
    suspend fun removeShoppingList(list_id: Int): RemoveStatus
    suspend fun createShoppingList(key: String, name: String): ListStatus
    suspend fun createTestKey(): String
    suspend fun authenticate(key: String): AuthStatus

}

class NetworkShoppingRepository (
    private val apiService: ApiService
) : ShoppingRepository {
    override suspend fun getShoppingList(list_id: Int): ShoppingList = apiService.getShoppingList(list_id)
    override suspend fun getAllMyShopLists(key: String): AllStatus = apiService.getAllMyShopLists(key)

    override suspend fun crossItOff(id: Int): CrossOffStatus = apiService.crossItOff(id)

    override suspend fun addToShoppingList(id: Int, value: String, n: Int): ItemStatus = apiService.addToShoppingList(id, value, n)

    override suspend fun removeShoppingList(list_id: Int): RemoveStatus = apiService.removeShoppingList(list_id)
    override suspend fun createShoppingList(key: String, name: String): ListStatus = apiService.createShoppingList(key,name)

    override suspend fun createTestKey(): String = apiService.createTestKey()
    override suspend fun authenticate(key: String): AuthStatus = apiService.authenticate(key)
}

