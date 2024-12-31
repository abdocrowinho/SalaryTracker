package com.example.msareefapp.Ui.Activites.EditProfile

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.domain.entitys.User
import com.example.msareefapp.Bases.BaseActivity
import com.example.msareefapp.databinding.ActivityEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileActivity :BaseActivity<ActivityEditProfileBinding,EditProfileViewModel>() {
  private  val _viewModel : EditProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel.getUserUseCase()
        observer()
        initViews()
    }

    private fun observer() {
        _viewModel.uiMessageLiveData.observe(this){
            it?.let {  showDialog(it) }

        }

        _viewModel.oldUserLiveData.observe(this){user->
            binding.apply {
                user?.let {
                        nickNameValue.setText(it.userName)
                        salaryValue.setText(it.salary)
                        notificationValue.setText(it.notification)
                        expectedValue.setText(it.expectedSavings)
                }
            }

        }
        _viewModel.username.observe(this){nickNameError->
            nickNameError?.let {
                binding.salaryContainer.isHelperTextEnabled=true
                binding.nickNameContainer.helperText= nickNameError.joinToString("\n")
            }
        }
        _viewModel.notificationLiveData.observe(this){notificationError->
            notificationError?.let {
                binding.notificationContainer.isHelperTextEnabled=true
                binding.notificationContainer.helperText=notificationError.joinToString("\n")

            }

        }
        _viewModel.salaryFieldLiveData.observe(this){salaryError->
            salaryError?.let {
                binding.salaryContainer.isHelperTextEnabled=true
                binding.salaryContainer.helperText=salaryError.joinToString("\n")

            }
        }
        _viewModel.expensesSalaryLiveData.observe(this){expectedSalaryError->
            expectedSalaryError?.let {
                binding.expectedContainer.isHelperTextEnabled=true
                binding.expectedContainer.helperText=expectedSalaryError.joinToString("\n")

            }

        }
    }

    private fun initViews() {
        binding.apply {
            updateBtn.setOnClickListener{
                val newUser = User(userId = 1, userName = nickNameValue.text.toString(), salary = salaryValue.text.toString()
                , notification = notificationValue.text.toString(), expectedSavings = expectedValue.text.toString()
                )
                _viewModel.updateButton(newUser)
            }
materialToolBar.setNavigationOnClickListener{
    finish()
}
        }


    }

    override fun initViewModel(): EditProfileViewModel {
        return _viewModel
    }

    override fun inflateBinding(): ActivityEditProfileBinding {
        return ActivityEditProfileBinding.inflate(layoutInflater)
    }
}