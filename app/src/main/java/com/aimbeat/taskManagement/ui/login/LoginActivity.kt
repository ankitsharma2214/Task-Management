package com.aimbeat.taskManagement.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import com.aimbeat.taskManagement.R
import com.aimbeat.taskManagement.databinding.ActivityLoginBinding
import com.aimbeat.taskManagement.ui.Base
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : Base<ActivityLoginBinding>(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding?.toolbar)

    }
}
