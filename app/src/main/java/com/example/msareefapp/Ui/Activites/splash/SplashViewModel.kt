package com.example.msareefapp.Ui.Activites.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.User
import com.example.domain.useCases.GetUserUseCase
import com.example.msareefapp.Bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SplashViewModel @Inject constructor(
 private   val useCase: GetUserUseCase
) : BaseViewModel() {
   private var _userLifeData =MutableLiveData<User?>()
    val getUserLifeData : LiveData<User?> get() = _userLifeData

    fun getUser(){
        viewModelScope.launch {
         val user=  useCase.invoke()
            if (user == null ){
                _userLifeData.postValue(null)
            }else{
                Log.e("user fetch",user.userName.toString())

                _userLifeData.postValue(user)

            }
        }
    }
}