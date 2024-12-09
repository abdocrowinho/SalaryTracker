package com.example.msareefapp.Ui.Activites.regestertion

import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.User
import com.example.domain.useCases.InsertUserUseCase
import com.example.msareefapp.Bases.BaseValidation.FieldsValidation
import com.example.msareefapp.Bases.BaseValidation.MinLengthValidator
import com.example.msareefapp.Bases.BaseValidation.NotEmptyValidator
import com.example.msareefapp.Bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val useCase: InsertUserUseCase
) : BaseViewModel() {
    private var _userLiveData = MutableLiveData<User>()
    val userLifeData: LiveData<User> get() = _userLiveData

    private var _nickNameLiveData = MutableLiveData<List<String>>()
    val nickNameLiveData: LiveData<List<String>> get() = _nickNameLiveData

    private var _salaryLiveData = MutableLiveData<List<String>>()
    val salaryLiveData: LiveData<List<String>> get() = _salaryLiveData

    private var _notificationLiveData = MutableLiveData<List<String>>()
    val notificationLiveData: LiveData<List<String>> get() = _notificationLiveData

    private var _savingMoneyLiveData = MutableLiveData<List<String>>()
    val savingMoneyLiveData: LiveData<List<String>> get() = _savingMoneyLiveData

    private var _resultValidation = MutableLiveData<Boolean>()
    val resultValidation: LiveData<Boolean> get() = _resultValidation


    fun animateText(textView: TextView, fullText: String, delay: Long = 100L) {
        textView.text = ""
        for (i in fullText.indices) {
            textView.postDelayed({
                textView.text = fullText.substring(0, i + 1)
            }, i * delay)
        }
    }

    fun animateTextFields(view: View, delay: Long = 50L) {
        view.translationX = view.rootView.width.toFloat()
        view.alpha = 0f

        view.animate().translationX(0f).alpha(1f).setDuration(1000).setStartDelay(delay).start()

    }

    private fun validateNickName(nickname: String) {
        val validator = listOf(NotEmptyValidator(), MinLengthValidator(6))
        _nickNameLiveData.value =
            FieldsValidation.validateFields(validators = validator, value = nickname)
    }

    private fun validateSalary(salary: String) {
        val validator = listOf(NotEmptyValidator(), MinLengthValidator(4))
        _salaryLiveData.value =
            FieldsValidation.validateFields(validators = validator, value = salary)
    }

    private fun validateNotification(notification: String) {
        val validator = listOf(NotEmptyValidator(), MinLengthValidator(1))
        _notificationLiveData.value =
            FieldsValidation.validateFields(validators = validator, value = notification)
    }

    private fun validateSavingMoney(savingMoney: String) {
        val validator = listOf(NotEmptyValidator(), MinLengthValidator(3))
        _savingMoneyLiveData.value =
            FieldsValidation.validateFields(validators = validator, value = savingMoney)
    }

    private fun validateAllFields(
        nickname: String,
        salary: String,
        notification: String,
        savingMoney: String
    ): Boolean {
        validateNickName(nickname)
        validateSalary(salary)
        validateNotification(notification)
        validateSavingMoney(savingMoney)

        val isValid =
            _nickNameLiveData.value.isNullOrEmpty() && _salaryLiveData.value.isNullOrEmpty()
                    && _notificationLiveData.value.isNullOrEmpty() && _savingMoneyLiveData.value.isNullOrEmpty()
        _resultValidation.value = isValid

        return isValid
    }

    fun sendFormClicked(user: User) {
        validateAllFields(
            user.userName.toString(),
            user.salary.toString(),
            user.notification.toString(),
            user.expectedSavings.toString()
        )
        if (_resultValidation.value==true) {
            insertUser(user)
        }
    }

     private fun insertUser(user: User) {
        viewModelScope.launch {
            useCase.invoke(user)
        }
    }

}