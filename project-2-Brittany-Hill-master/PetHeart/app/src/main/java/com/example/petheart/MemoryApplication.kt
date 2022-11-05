package com.example.petheart

import android.app.Application

class MemoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MemoryRepository.initialize(this)
    }
}