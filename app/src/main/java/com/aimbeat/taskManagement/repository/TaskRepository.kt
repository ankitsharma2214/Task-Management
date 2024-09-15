package com.aimbeat.taskManagement.repository

import com.aimbeat.taskManagement.db.TaskDao
import com.aimbeat.taskManagement.ui.home.model.TaskModel
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    suspend fun addTask(taskModel: TaskModel) = taskDao.addTask(taskModel)

    suspend fun getTask() = taskDao.getTask()

    suspend fun update(taskModel: TaskModel) = taskDao.update(taskModel)

    suspend fun delete(taskModel: TaskModel) = taskDao.delete(taskModel)

}