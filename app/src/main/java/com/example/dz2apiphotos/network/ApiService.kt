package com.example.dz2apiphotos.network

import com.example.dz2apiphotos.model.AllStatus
import com.example.dz2apiphotos.model.AuthStatus
import com.example.dz2apiphotos.model.ItemStatus
import com.example.dz2apiphotos.model.ListStatus
import com.example.dz2apiphotos.model.RemoveStatus
import com.example.dz2apiphotos.model.ShoppingList
import kotlinx.serialization.SerialName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("CreateTestKey?")
    suspend fun createTestKey(): String

    @POST("Authentication?")
    suspend fun authenticate(
        @Query("key") key: String,
    ): AuthStatus

    @POST("CreateShoppingList?")
    suspend fun createShoppingList(
        @Query("key") key: String,
        @Query("name") name: String
    ): Call<ListStatus>
    @POST("RemoveShoppingList?")
    suspend fun removeShoppingList(
        @Query("list_id") list_id: Int
    ): Call<RemoveStatus>
    @POST("AddToShoppingList?")
    suspend fun addToShoppingList(
        @Query("id") id: Int,
        @Query("value") value: String,
        @Query("n") amount: Int
    ): Call<ItemStatus>
    @POST("CrossItOff?")
    suspend fun crossItOff(
        @Query("id") id: Int
    ): Call<RemoveStatus>
    @POST("GetAllMyShopLists?")
    suspend fun getAllMyShopLists(
        @Query("key") key: String,
    ): Call<AllStatus>
    @GET("GetShoppingList?")
    suspend fun getShoppingList(
        @Query("list_id") list_id: Int
    ): ShoppingList


}

//S2SADY
//https://cyberprot.ru/shopping/v1/GetAllMyShopLists?key=S2SADY
//https://cyberprot.ru/shopping/v1/CroosItOff??list_id=403&item_id=499

