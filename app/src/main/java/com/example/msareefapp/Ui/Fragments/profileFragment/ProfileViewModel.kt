package com.example.msareefapp.Ui.Fragments.profileFragment

import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.User
import com.example.domain.useCases.userUseCase.UpdateUserUseCase
import com.example.msareefapp.Bases.BaseValidation.FieldsValidation
import com.example.msareefapp.Bases.BaseValidation.MinLengthValidator
import com.example.msareefapp.Bases.BaseValidation.NotEmptyValidator
import com.example.msareefapp.Bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase

) : BaseViewModel(){
    private var _usernameLiveData=MutableLiveData<List<String>>()
    val username : LiveData<List<String>>get() = _usernameLiveData
    private var _salaryFieldLiveData=MutableLiveData<List<String>>()
    val salaryFieldLiveData : LiveData<List<String>>get() = _salaryFieldLiveData
    private var _notificationLiveData=MutableLiveData<List<String>>()
    val notificationLiveData : LiveData<List<String>>get() = _notificationLiveData
    private var _expensesSalaryLiveData=MutableLiveData<List<String>>()
    val expensesSalaryLiveData : LiveData<List<String>>get() = _expensesSalaryLiveData
    private var _resultValidateLiveData=MutableLiveData<Boolean>()
    val resultValidateLiveData : LiveData<Boolean>get() = _resultValidateLiveData

 private fun validateNickName(nickname: String){
     val validator = listOf(MinLengthValidator(5),NotEmptyValidator())
 _usernameLiveData.value =    FieldsValidation.validateFields(validators =  validator, value = nickname)
 }
    private fun validateSalary(salary: String){
        val validator = listOf(MinLengthValidator(4),NotEmptyValidator())
        _salaryFieldLiveData.value = FieldsValidation.validateFields(validators =  validator, value = salary)
    }
    private fun validateNotification(notification: String){
        val validator = listOf(MinLengthValidator(1),NotEmptyValidator())
        _notificationLiveData.value =    FieldsValidation.validateFields(validators =  validator, value = notification)
    }
    private fun validateExpenses(expenses: String){
        val validator = listOf(MinLengthValidator(3),NotEmptyValidator())
        _expensesSalaryLiveData.value =    FieldsValidation.validateFields(validators =  validator, value = expenses)
    }
    fun validateAllFields(
        userName : String,
        salary : String,
        notification : String,
        expensesSalary : String
    ):Boolean{
        validateNickName(userName)
        validateSalary(salary)
        validateNotification(notification)
        validateExpenses(expensesSalary)

      val  isValid = _salaryFieldLiveData.value.isNullOrEmpty() && _notificationLiveData.value.isNullOrEmpty() &&
              _salaryFieldLiveData.value.isNullOrEmpty()&&_expensesSalaryLiveData.value.isNullOrEmpty()

        _resultValidateLiveData.value= isValid

        return isValid

    }

    fun updateUser(user: User){
        viewModelScope.launch {
            updateUserUseCase.invoke(user)
        }
    }

}