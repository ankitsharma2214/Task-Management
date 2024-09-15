package com.aimbeat.taskManagement

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskManagementApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}