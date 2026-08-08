package com.example.a3_education_app

import android.app.Application
import com.example.a3_education_app.data.AppContainer
import com.example.a3_education_app.data.DefaultAppContainer

class SpaceEducationApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}