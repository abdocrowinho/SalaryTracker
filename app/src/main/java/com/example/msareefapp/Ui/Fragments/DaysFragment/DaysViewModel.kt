package com.example.msareefapp.Ui.Fragments.DaysFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.DaySpending
import com.example.domain.useCases.StaticsUseCases.GetSpendingByDayUseCase
import com.example.msareefapp.Bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaysViewModel @Inject constructor(

    val getSpendingByDay: GetSpendingByDayUseCase
)
    :BaseViewModel() {
       private var _spendingDaysLiveData = MutableLiveData<List<DaySpending?>?>()
    val spendingDaysLiveData : LiveData<List<DaySpending?>?> get() = _spendingDaysLiveData

    fun getSpendingDay(){

        viewModelScope.launch {
            getSpendingByDay.invoke().collect{spendingDays->
                Log.d("SpendingDays is ", spendingDays.toString())
                _spendingDaysLiveData.postValue(spendingDays)
            }
        }
    }

}