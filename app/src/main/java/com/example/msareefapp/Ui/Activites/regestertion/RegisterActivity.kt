package com.example.msareefapp.Ui.Activites.regestertion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.msareefapp.Bases.BaseActivity
import com.example.msareefapp.R
import com.example.msareefapp.databinding.ActivityRegesterBinding

class RegisterActivity : BaseActivity<ActivityRegesterBinding,RegisterViewModel>() {
   private val _viewModel :RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       _viewModel.animateText(binding.introOfRegister,getString(R.string.intro_of_register))
        _viewModel.animateTextFields(binding.nameContainer, delay = 10000L)
        _viewModel.animateTextFields(binding.salaryContainer, delay = 11000L)
        _viewModel.animateTextFields(binding.notificationContainer, delay = 12000L)
        _viewModel.animateTextFields(binding.savingContainer, delay = 13000L)
        _viewModel.animateTextFields(binding.sendBtn,14000L)


    }

    override fun initViewModel(): RegisterViewModel {
        return _viewModel

    }

    override fun inflateBinding(): ActivityRegesterBinding {
       return ActivityRegesterBinding.inflate(layoutInflater)
    }
}