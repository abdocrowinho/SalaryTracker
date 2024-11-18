package com.example.msareefapp.Ui.Activites.regestertion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.domain.entitys.User
import com.example.msareefapp.Bases.BaseActivity
import com.example.msareefapp.R
import com.example.msareefapp.Ui.Activites.mainActivity.MainActivity
import com.example.msareefapp.databinding.ActivityRegesterBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegesterBinding, RegisterViewModel>() {
    private val _viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observers()


    }

    private fun observers() {
        _viewModel.nickNameLiveData.observe(this) { error ->
            if (error.isEmpty()) {
                binding.nameContainer.helperText = null
            } else {
                binding.nameContainer.helperText = error.joinToString("\n")
            }
        }
        _viewModel.salaryLiveData.observe(this) { error ->
            if (error.isEmpty()) {
                binding.salaryContainer.helperText = null
            } else {
                binding.salaryContainer.helperText = error.joinToString("\n")
            }

        }
        _viewModel.notificationLiveData.observe(this) { error ->
            if (error.isEmpty()) {
                binding.notificationContainer.helperText = null
            } else {
                binding.notificationContainer.helperText = error.joinToString("\n")
            }

        }
        _viewModel.savingMoneyLiveData.observe(this) { error ->
            if (error.isEmpty()) {
                binding.savingContainer.helperText = null
            } else {
                binding.savingContainer.helperText = error.joinToString("\n")
            }

        }
        _viewModel.resultValidation.observe(this){isValid->
            if (isValid){
                Log.d("SnackbarDebug", "isValid: $isValid")
                showSnackBar()
                Log.d("SnackbarDebug", "Root: ${binding.root}")
            }

        }
    }

    private fun showSnackBar() {
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.register_done), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.ok)) {
                navigateToMainActivity()
            }
            .show()
    }

    private fun navigateToMainActivity() {
        val intent=Intent(this ,MainActivity::class.java )
        startActivity(intent)
    }


    private fun initViews() {
        textDelay()
        binding.sendBtn.setOnClickListener {
            _viewModel.sendFormClicked(
                User(
                    userName = binding.nameText.text.toString(),
                    salary = binding.salaryText.text.toString(),
                    notification = binding.notificationText.text.toString(),
                    expectedSavings = binding.savingName.text.toString()
                )
            )
        }
    }

    private fun textDelay() {
        _viewModel.animateText(binding.introOfRegister, getString(R.string.intro_of_register))
        _viewModel.animateTextFields(binding.nameContainer, delay = 10000L)
        _viewModel.animateTextFields(binding.salaryContainer, delay = 11000L)
        _viewModel.animateTextFields(binding.notificationContainer, delay = 12000L)
        _viewModel.animateTextFields(binding.savingContainer, delay = 13000L)
        _viewModel.animateTextFields(binding.sendBtn, 14000L)
    }

    override fun initViewModel(): RegisterViewModel {
        return _viewModel

    }

    override fun inflateBinding(): ActivityRegesterBinding {
        return ActivityRegesterBinding.inflate(layoutInflater)
    }
}