package com.example.msareefapp.Ui.Activites.CategoryInvoices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.Invoice
import com.example.domain.useCases.InvoicesUseCases.GetInvoicesByCategoryIdUseCase
import com.example.msareefapp.Bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryInvoicesViewModel @Inject constructor(
    private val getInvoicesByCategoryIdUseCase: GetInvoicesByCategoryIdUseCase
) : BaseViewModel() {
    private var _invoicesByCategoryLiveData = MutableLiveData<List<Invoice>>()
    val invoicesByCategoryLiveData: LiveData<List<Invoice>> get() = _invoicesByCategoryLiveData
    fun getInvoicesByCategoryId(categoryId: String) {
        viewModelScope.launch {
            getInvoicesByCategoryIdUseCase.invoke(categoryId)
                .collect { invoicesList ->
                    _invoicesByCategoryLiveData.postValue(invoicesList)
                }


        }
    }
}