package com.aimbeat.taskManagement.ui.login.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aimbeat.taskManagement.R
import com.aimbeat.taskManagement.databinding.FragmentAuthBinding
import com.aimbeat.taskManagement.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (sharedPreferences.getString("email", null) != null) {


            findNavController().navigate(R.id.homeactivity)
            activity?.finish()

        } else {
            binding?.loginButton?.setOnClickListener {
                findNavController().navigate(R.id.loginfragment)
            }

            binding?.registerButton?.setOnClickListener {
                findNavController().navigate(R.id.registrationfragment)
            }
        }


    }
}