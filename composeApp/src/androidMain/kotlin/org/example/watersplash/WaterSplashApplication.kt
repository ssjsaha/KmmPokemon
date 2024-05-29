package org.example.watersplash

import KoinInitializer
import android.app.Application

class WaterSplashApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}