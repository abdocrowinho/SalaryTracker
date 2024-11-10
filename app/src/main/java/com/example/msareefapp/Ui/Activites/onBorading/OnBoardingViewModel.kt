package com.example.msareefapp.Ui.Activites.onBorading

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.msareefapp.Bases.BaseViewModel
import com.example.msareefapp.Ui.Activites.regestertion.RegisterActivity

class OnBoardingViewModel : BaseViewModel() {
    private var _positionLiveData = MutableLiveData(0)
    val position: LiveData<Int> get() = _positionLiveData
    private var _navigateLifeData = MutableLiveData<Boolean>()
    val navigateLifeData: LiveData<Boolean> get() = _navigateLifeData

   private var itemCount = 0
    fun setItemCount(itemCount:Int){
       this.itemCount  = itemCount
    }

     fun navigateToNextItem() {
        val currentPosition = _positionLiveData.value ?:0

        if (currentPosition < ((itemCount - 1))) {
            _positionLiveData.value = currentPosition+1

        } else {
_navigateLifeData.value=true
        }
    }

    fun navigateToRegisterScreen(){
        _navigateLifeData.value=false
    }
}