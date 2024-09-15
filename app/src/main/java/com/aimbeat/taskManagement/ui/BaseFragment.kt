package com.aimbeat.taskManagement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.aimbeat.taskManagement.util.Inflate


open class BaseFragment<VB : ViewDataBinding>(private val layoutInt: Inflate<VB>) : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = layoutInt.invoke(inflater, container, false)
        _binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root
    }
}