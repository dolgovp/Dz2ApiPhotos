package com.example.dz2apiphotos

import android.app.Application
import com.example.dz2apiphotos.network.AppContainer
import com.example.dz2apiphotos.network.DefaultAppContainer

class PhotosApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}