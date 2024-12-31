package com.example.msareefapp.Ui.Activites.EditProfile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.User
import com.example.domain.useCases.StaticsUseCases.GetUserUseCase
import com.example.domain.useCases.userUseCase.UpdateUserUseCase
import com.example.msareefapp.Bases.BaseValidation.FieldsValidation
import com.example.msareefapp.Bases.BaseValidation.MinLengthValidator
import com.example.msareefapp.Bases.BaseValidation.NotEmptyValidator
import com.example.msareefapp.Bases.BaseViewModel
import com.example.msareefapp.Bases.UiMessage
import com.example.msareefapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
  private  val getUserUseCase: GetUserUseCase,
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
    private var _oldUserLiveData = MutableLiveData<User>()
    val oldUserLiveData : LiveData<User> get ()=_oldUserLiveData
    private fun validateNickName(nickname: String){
        val validator = listOf(MinLengthValidator(5), NotEmptyValidator())
        _usernameLiveData.value =    FieldsValidation.validateFields(validators =  validator, value = nickname)
    }
    private fun validateSalary(salary: String){
        val validator = listOf(MinLengthValidator(4), NotEmptyValidator())
        _salaryFieldLiveData.value = FieldsValidation.validateFields(validators =  validator, value = salary)
    }
    private fun validateNotification(notification: String){
        val validator = listOf(MinLengthValidator(1), NotEmptyValidator())
        _notificationLiveData.value =    FieldsValidation.validateFields(validators =  validator, value = notification)
    }
    private fun validateExpenses(expenses: String){
        val validator = listOf(MinLengthValidator(3), NotEmptyValidator())
        _expensesSalaryLiveData.value =    FieldsValidation.validateFields(validators =  validator, value = expenses)
    }
    private fun validateAllFields(
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


   fun updateButton(user: User){
        if (validateAllFields(userName = user.userName!!, salary = user.salary!!,
                notification = user.notification!!, expensesSalary = user.expectedSavings!!)){
            updateUser(user)
            val uiMessage = UiMessage.Builder().setMessageId(R.string.user_update_correctly).build()
            handleUiMessage(uiMessage)

        }else{
            val uiMessage = UiMessage.Builder().setMessageId(R.string.all_fields_should_not_be_null).build()
            handleUiMessage(uiMessage)
        }
    }

    private fun updateUser(user: User){
        viewModelScope.launch {
            updateUserUseCase.invoke(user)
        }
    }
    fun getUserUseCase(){
        viewModelScope.launch {
            val oldUser = getUserUseCase.invoke()
            _oldUserLiveData.postValue(oldUser!!)
        }
    }

}