package com.example.msareefapp.Bases

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<vb : ViewBinding, Vm : BaseViewModel> : AppCompatActivity() {
    private var _binding: vb? = null
    val binding: vb get() = _binding!!

    lateinit var viewModel: Vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateBinding()
        setContentView(binding.root)
        setContentView(binding.root)
        viewModel = initViewModel()

    }
    abstract fun initViewModel():Vm

    abstract fun inflateBinding(): vb

}