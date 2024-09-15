package com.aimbeat.taskManagement.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aimbeat.taskManagement.ui.home.model.TaskModel

@Database(
    entities = [TaskModel::class],
    version = 1
)

abstract class TaskDB : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile         // Multiple Thereads can Access TaskDB at a Single Time
        private var taskdb: TaskDB? = null

        fun getDataBase(context: Context): TaskDB {

            return taskdb ?: synchronized(this) {   // Continous not allowing to use thered
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDB::class.java,
                    "TASK_DATABASE"
                ).build()
                taskdb = instance
                instance
            }
        }

    }
}