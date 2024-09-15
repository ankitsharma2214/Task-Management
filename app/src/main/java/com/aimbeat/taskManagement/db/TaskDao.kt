package com.aimbeat.taskManagement.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aimbeat.taskManagement.ui.home.model.TaskModel

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(
        taskModel: TaskModel
    )

    @Query("SELECT * FROM task_table")
    fun getTask(): List<TaskModel>

    @Update
    fun update(taskModel: TaskModel)

    @Delete
    fun delete(taskModel: TaskModel)
}