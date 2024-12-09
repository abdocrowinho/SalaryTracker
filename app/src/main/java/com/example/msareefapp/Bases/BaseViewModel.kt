package com.example.msareefapp.Bases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
   private var _uiMessageLiveData = MutableLiveData<UiMessage?>()
   val uiMessageLiveData : LiveData<UiMessage?> get() = _uiMessageLiveData

    fun handleUiMessage(uiMessage: UiMessage){
        _uiMessageLiveData.value = uiMessage
    }
    fun clearUiMessage(){
        _uiMessageLiveData.value = null
    }
}