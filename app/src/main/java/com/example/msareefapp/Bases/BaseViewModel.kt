package com.example.msareefapp.Bases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entitys.Invoice

open class BaseViewModel : ViewModel() {
   private var _uiMessageLiveData = SingleLiveData<UiMessage?>()
   val uiMessageLiveData : LiveData<UiMessage?> get() = _uiMessageLiveData

    fun handleUiMessage(uiMessage: UiMessage?){
        _uiMessageLiveData.value = uiMessage
    }
    fun clearUiMessage(){
        _uiMessageLiveData.postValue(null)
    }
}