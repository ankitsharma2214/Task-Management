package com.aimbeat.taskManagement.ui.login.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.aimbeat.taskManagement.databinding.FragmentRegistrationBinding
import com.aimbeat.taskManagement.ui.BaseFragment
import com.aimbeat.taskManagement.ui.login.AuthVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private val authVM: AuthVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            binding?.registerVM = authVM
            registerBtn.setOnClickListener {
                authVM.registerAuth()
            }
        }
    }
}