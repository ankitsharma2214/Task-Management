package com.aimbeat.taskManagement.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.aimbeat.taskManagement.db.TaskDB
import com.aimbeat.taskManagement.db.TaskDao
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides  // Instance Provider
    @Singleton // Create one Instance throughout the project
    fun fireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()

    }

    @Provides
    @Singleton
    fun dbProvider(@ApplicationContext context: Context): TaskDB {
        return Room.databaseBuilder(context, TaskDB::class.java, "TASK_DATABASE").build()
    }


    @Provides
    fun taskDaoProvider(taskDb: TaskDB): TaskDao {
        return taskDb.taskDao()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesEditor(prefs: SharedPreferences): SharedPreferences.Editor {
        return prefs.edit()
    }

}