package com.aimbeat.taskManagement.ui.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskModel(
    @PrimaryKey(autoGenerate = true) var taskID: Int = 0,
    var title: String,
    var desccription: String,
    var dueDate: String,
    var status: String,
)
