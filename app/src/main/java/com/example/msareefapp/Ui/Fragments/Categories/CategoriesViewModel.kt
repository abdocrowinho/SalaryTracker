package com.example.msareefapp.Ui.Fragments.Categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.CategoryStats
import com.example.domain.useCases.StaticsUseCases.GetCategoryUseCase
import com.example.domain.useCases.categoriesUseCase.GetCategoriesUseCase
import com.example.msareefapp.Bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoryUseCase
)
    : BaseViewModel() {
        private var _categoriesLiveData = MutableLiveData<List<CategoryStats?>?>()
    val categoriesLiveData : LiveData<List<CategoryStats?>?>get() = _categoriesLiveData

    fun getCategoriesStats(){
        viewModelScope.launch {
            val categoriesStatsList = getCategoriesUseCase.invoke(1)
            _categoriesLiveData.postValue(categoriesStatsList)
        }
    }
}