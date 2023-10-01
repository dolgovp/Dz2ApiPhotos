package com.example.dz2apiphotos.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface AppContainer{
    val shoppingRepository: ShoppingRepository
}
class DefaultAppContainer : AppContainer{
    //private  val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
    //private val BASE_PICSUM_URL = "https://picsum.photos"
    private val BASE_UNISAFE_URL = "https://cyberprot.ru/shopping/v1/"


    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_UNISAFE_URL)
        .build()

    private val retrofitService: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }

    override val shoppingRepository: ShoppingRepository by lazy {
        NetworkShoppingRepository(retrofitService)
    }


}


