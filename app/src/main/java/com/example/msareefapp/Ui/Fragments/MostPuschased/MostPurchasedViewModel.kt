package com.example.msareefapp.Ui.Fragments.MostPuschased

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.MostPurchased
import com.example.domain.useCases.StaticsUseCases.GetMostPurchasedItemsUseCase
import com.example.msareefapp.Bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostPurchasedViewModel @Inject constructor(
    private val getMostPurchasedItemsUseCase: GetMostPurchasedItemsUseCase
) : BaseViewModel() {
    private var _mostPurchasedItemsLivedata = MutableLiveData<List<MostPurchased?>?>()
    val mostPurchasedItemsLivedata : LiveData<List<MostPurchased?>?> get() = _mostPurchasedItemsLivedata

    fun getMostPurchasedItem(){
        viewModelScope.launch {
            getMostPurchasedItemsUseCase.invoke().collect{mostPurchasedList->
                _mostPurchasedItemsLivedata.postValue(mostPurchasedList)
            }
        }
    }
}