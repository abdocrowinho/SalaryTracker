package com.example.msareefapp.Ui.Fragments.Statics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.CategoryStats
import com.example.domain.entitys.User
import com.example.domain.useCases.StaticsUseCases.GetCategoryUseCase
import com.example.domain.useCases.StaticsUseCases.GetUserUseCase
import com.example.msareefapp.Bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class StaticsViewMode @Inject constructor
    (private val getUserUseCase: GetUserUseCase, private val getCategoryStatsUseCase: GetCategoryUseCase) :
    BaseViewModel() {
    private var _userLiveData=MutableLiveData<User>()
    val userLiveData : LiveData<User> get () = _userLiveData
   private var _categoryStats = MutableLiveData<List<CategoryStats?>?>()
    val categoryStats:LiveData<List<CategoryStats?>?> get() = _categoryStats

    fun getUserData(){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke()
            Log.d("user in staticsVM" , "user is $user")
       _userLiveData.postValue(user!!)

        }
    }
    fun getCategoryStats( userId : Int){
        viewModelScope.launch(Dispatchers.IO) {
            val  categoriesStats = getCategoryStatsUseCase.invoke(userId)
            Log.d("user in staticsVM" , "user is $categoriesStats")

            _categoryStats.postValue(categoriesStats)

        }
    }
}