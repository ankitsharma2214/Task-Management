package com.aimbeat.taskManagement.ui.home.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.aimbeat.taskManagement.databinding.FragmentAddTaskBinding
import com.aimbeat.taskManagement.databinding.FragmentHomeBinding
import com.aimbeat.taskManagement.ui.BaseFragment
import com.aimbeat.taskManagement.ui.adapter.TaskListAdapter
import com.aimbeat.taskManagement.ui.home.HomeVM
import com.aimbeat.taskManagement.ui.home.model.TaskModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    TaskListAdapter.TaskListner {

    private val homevm: HomeVM by viewModels()
    private val CHANNEL_ID = "task_channel"
    private lateinit var taskListAdapter: TaskListAdapter
    private var allTasks: List<TaskModel> = listOf()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskListAdapter = TaskListAdapter(requireContext(), listOf(), this)
        binding?.rvTasklist?.adapter = taskListAdapter

        observal()
        homevm.getTask()


        binding?.apply {
            ivAddBtn.setOnClickListener {
                addTaskDialog(null, 1)
            }

            // Set up the search EditText
            etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    filterTasks(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    fun addTaskDialog(taskModel1: TaskModel? = null, i: Int) {
        val addbottomShot =
            BottomSheetDialog(requireContext())
        val binding = FragmentAddTaskBinding.inflate(layoutInflater)
        addbottomShot.setContentView(binding.root)
        binding.homevm = homevm
        if (i == 2) {
            binding.etTitle.setText(taskModel1?.title)
            binding.etDesc.setText(taskModel1?.desccription)
            binding.etDueDate.setText(taskModel1?.dueDate)
            binding.etStatus.setText(taskModel1?.status)
        }
        binding.btnAddTask.setOnClickListener {

            // Retrieve the input values
            val title = binding.etTitle.text.toString().trim()
            val description = binding.etDesc.text.toString().trim()
            val dueDate = binding.etDueDate.text.toString().trim()
            val status = binding.etStatus.text.toString().trim()

            // Validate the input fields
            if (title.isEmpty()) {
                binding.etTitle.error = "Title is required"
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                binding.etDesc.error = "Description is required"
                return@setOnClickListener
            }

            if (dueDate.isEmpty()) {
                binding.etDueDate.error = "Due date is required"
                return@setOnClickListener
            }

            if (status.isEmpty()) {
                binding.etStatus.error = "Status is required"
                return@setOnClickListener
            }

            // If validation passes, proceed with task creation or update
            if (i == 1) {
                val taskModel = TaskModel(
                    taskID = 0,
                    title = title,
                    desccription = description,
                    dueDate = dueDate,
                    status = status
                )

                homevm.addTask(taskModel)
                Toast.makeText(context, "Task Added successfully", Toast.LENGTH_SHORT).show()

                addbottomShot.dismiss()


            } else {
                homevm.updateTask(
                    TaskModel(
                        taskModel1?.taskID ?: 0,
                        title = title,
                        desccription = description,
                        dueDate = dueDate,
                        status = status
                    )
                )
            }

            // Retrieve the updated task list
            homevm.getTask()
            Toast.makeText(context, "Task Updated successfully", Toast.LENGTH_SHORT).show()
            showNotification("Task Notification", "Task $status")
            addbottomShot.dismiss()

        }
        addbottomShot.show()

    }

    // Observe the fetch Data

    fun observal() {
        homevm.homedata.observe(viewLifecycleOwner) { arrayList ->
            Log.v("CHECK_DATA", "" + arrayList)
            allTasks = arrayList
            taskListAdapter.updateTasks(arrayList)
            binding?.rvTasklist?.adapter =
                TaskListAdapter(requireContext(), arrayList, listner = this)
        }
    }

    override fun update(taskModel: TaskModel) {
        addTaskDialog(taskModel, 2)
    }

    override fun delete(taskModel: TaskModel) {
        homevm.deleteTask(taskModel)
    }

    private fun filterTasks(query: String) {
        val filteredTasks = allTasks.filter { it.status.contains(query, ignoreCase = true) }
        taskListAdapter.updateTasks(filteredTasks)
    }


    private fun showNotification(title: String, message: String) {
        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // For Android O and above, create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Task Channel"
            val descriptionText = "Channel for Task Notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_notification_overlay) // You can use your app icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Show the notification
        notificationManager.notify(0, notification)
    }
}