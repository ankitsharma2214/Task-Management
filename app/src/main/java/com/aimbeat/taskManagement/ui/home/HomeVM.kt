package com.aimbeat.taskManagement.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aimbeat.taskManagement.repository.TaskRepository
import com.aimbeat.taskManagement.ui.home.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val taskRepository: TaskRepository

) : ViewModel() {

    var _homedata = MutableLiveData<List<TaskModel>>()
    val homedata: LiveData<List<TaskModel>> get() = _homedata

    fun addTask(taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) { taskRepository.addTask(taskModel) }
    }

    fun getTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val getTask = taskRepository.getTask()
            _homedata.postValue(getTask)
        }
    }

    fun deleteTask(taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.delete(taskModel)
            getTask()

        }
    }

    fun updateTask(taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.update(taskModel)
            getTask()
        }
    }
}