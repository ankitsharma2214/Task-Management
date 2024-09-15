package com.aimbeat.taskManagement.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aimbeat.taskManagement.databinding.TasklistItemBinding
import com.aimbeat.taskManagement.ui.home.model.TaskModel

class TaskListAdapter(
    var context: Context,
    var taskmodelList: List<TaskModel>,
    var listner: TaskListner
) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    interface TaskListner {
        fun update(taskModel: TaskModel)
        fun delete(taskModel: TaskModel)
    }

    class ViewHolder(var iteamview: TasklistItemBinding) : RecyclerView.ViewHolder(iteamview.root) {
        fun loadIteam(taskModel: TaskModel, listner: TaskListner) {
            iteamview.tvTitle.text = taskModel.title
            iteamview.tvDesc.text = taskModel.desccription
            iteamview.tvDueDate.text = taskModel.dueDate
            iteamview.tvStatus.text = taskModel.status

            iteamview.ivUpdate.setOnClickListener {
                listner.update(taskModel)
            }

            iteamview.ivDelete.setOnClickListener {
                listner.delete(taskModel)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.ViewHolder {
        return ViewHolder(TasklistItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: TaskListAdapter.ViewHolder, position: Int) {
        holder.loadIteam(taskmodelList.get(position), listner)

    }

    override fun getItemCount(): Int {
        return taskmodelList.size
    }

    fun updateTasks(newTasks: List<TaskModel>) {
        taskmodelList = newTasks
        notifyDataSetChanged()
    }

}