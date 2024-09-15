package com.aimbeat.taskManagement.ui.login.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aimbeat.taskManagement.R
import com.aimbeat.taskManagement.databinding.FragmentLoginBinding
import com.aimbeat.taskManagement.ui.BaseFragment
import com.aimbeat.taskManagement.ui.login.AuthVM
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val authVM: AuthVM by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var editor: SharedPreferences.Editor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            binding?.loginVM = authVM
            loginbtn.setOnClickListener {
                authVM.loginAuth()
            }
        }
        authVM.logindata.observe(viewLifecycleOwner) {
            if (it == 1) {
                Toast.makeText(requireContext(), "Please Enter valid  Email & Passowrd !!", Toast.LENGTH_SHORT
                ).show()
            } else {
                findNavController().navigate(R.id.homeactivity)
                Toast.makeText(requireContext(), "Login Successfully !! ", Toast.LENGTH_SHORT)
                    .show()
                activity?.finish()

            }
        }
    }
}