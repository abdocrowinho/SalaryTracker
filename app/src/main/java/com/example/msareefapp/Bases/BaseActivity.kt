package com.example.msareefapp.Bases

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<vb : ViewBinding, Vm : BaseViewModel> : AppCompatActivity() {
    private var _binding: vb? = null
    val binding: vb? get() = _binding

    lateinit var viewModel: Vm

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        _binding = DataBindingUtil.setContentView(this, initViews())
        viewModel = initViewModel()

    }
    abstract fun initViewModel():Vm

    abstract fun initViews(): Int

}