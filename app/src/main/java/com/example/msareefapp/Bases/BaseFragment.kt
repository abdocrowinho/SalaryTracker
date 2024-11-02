package com.example.msareefapp.Bases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<vb : ViewBinding,
        VM : BaseViewModel> : Fragment() {

    private var _binding: vb? = null
    val binding get() = _binding
    lateinit var viewModel: VM
    abstract fun initViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, initViews(), container, false)
        return binding?.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun initViews(): Int

}