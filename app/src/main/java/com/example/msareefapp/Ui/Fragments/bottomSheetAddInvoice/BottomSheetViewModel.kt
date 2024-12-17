package com.example.msareefapp.Ui.Fragments.bottomSheetAddInvoice

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.Category
import com.example.domain.entitys.Invoice
import com.example.domain.entitys.PurchasedItem
import com.example.domain.useCases.InvoicesUseCases.InsertInvoicesUseCase
import com.example.domain.useCases.categoriesUseCase.GetCategoriesUseCase
import com.example.msareefapp.Bases.BaseValidation.FieldsValidation
import com.example.msareefapp.Bases.BaseValidation.MinLengthValidator
import com.example.msareefapp.Bases.BaseValidation.NotEmptyValidator
import com.example.msareefapp.Bases.BaseViewModel
import com.example.msareefapp.Bases.UiMessage
import com.example.msareefapp.R
import com.example.msareefapp.Ui.Fragments.Inovice.InvoiceAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(

    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel(){

   private var _getCategoriesLiveData = MutableLiveData<List<String?>?>()
    val getCategoriesLiveData : LiveData<List<String?>?>get() = _getCategoriesLiveData

    fun getCategories(){
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect{ categories->
                _getCategoriesLiveData.postValue(categories)
            }
        }

    }
}