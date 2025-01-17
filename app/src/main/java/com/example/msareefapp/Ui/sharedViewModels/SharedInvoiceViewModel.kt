package com.example.msareefapp.Ui.sharedViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.Invoice
import com.example.domain.useCases.InvoicesUseCases.GetAllInvoicesUseCase
import com.example.domain.useCases.InvoicesUseCases.InsertInvoicesUseCase
import com.example.domain.useCases.InvoicesUseCases.InsertPurchasedItemUseCase
import com.example.domain.useCases.InvoicesUseCases.UpdateInvoiceUseCase
import com.example.domain.useCases.categoriesUseCase.GetCategoriesUseCase
import com.example.msareefapp.Bases.BaseValidation.FieldsValidation
import com.example.msareefapp.Bases.BaseValidation.MinLengthValidator
import com.example.msareefapp.Bases.BaseValidation.NotEmptyValidator
import com.example.msareefapp.Bases.BaseViewModel
import com.example.msareefapp.Bases.UiMessage
import com.example.msareefapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedInvoiceViewModel @Inject constructor(
    private val insertInvoicesUseCase: InsertInvoicesUseCase,
    private val getAllInvoicesUseCase: GetAllInvoicesUseCase,
    private val updateInvoiceUseCase: UpdateInvoiceUseCase,
    private  val getCategoriesUseCase: GetCategoriesUseCase,
    private val insertPurchasedItemUseCase: InsertPurchasedItemUseCase
) :BaseViewModel() {
    private  var _invoiceTypeLiveData = MutableLiveData<List<String?>?>()
    val invoiceTypeLiveData : LiveData<List<String?>?> get() = _invoiceTypeLiveData

    private var  _invoicesLiveData = MutableLiveData<List<Invoice?>?>()
    val invoicesLiveData : LiveData<List<Invoice?>?> get() = _invoicesLiveData

    private var _categoriesLiveData = MutableLiveData<List<String?>?>()
    val categoriesLiveData : LiveData<List<String?>?>get() = _categoriesLiveData

    fun getAllInvoices(){
        viewModelScope.launch {
            getAllInvoicesUseCase.invoke().collect {
                _invoicesLiveData.postValue(it)
            }
        }
    }

    private fun insertInvoice(invoice : Invoice, categoryName:String){
        viewModelScope.launch {
            insertInvoicesUseCase.invoke(invoice,categoryName)
            insertPurchasedItemUseCase.invoke(invoice.purchasedItems)
        }
    }

    private fun validateInvoiceType(invoiceType: String) {
        val validator = listOf(NotEmptyValidator(), MinLengthValidator(5))
        _invoiceTypeLiveData.value =
            FieldsValidation.validateFields(validators = validator, value = invoiceType)
    }
    fun doneButton(isValid : Boolean, invoice: Invoice, invoiceType: String ){
        validateInvoiceType(invoiceType)
        if (invoiceTypeLiveData.value.isNullOrEmpty()){
            if (isValid){
                insertInvoice(invoice,invoiceType)

                _invoicesLiveData.value = _invoicesLiveData.value?.plus(invoice)
                val uiMessage =  UiMessage.Builder().setMessageId(R.string.invoice_inserted_correctly).build()
                handleUiMessage(uiMessage = uiMessage)
                Log.d("uiMessage",uiMessage.message.toString())

            }else {
                val uiMessage =  UiMessage.Builder().setMessageId(R.string.invalid_invoice).setIsCancelable(true) .build()
                handleUiMessage(uiMessage = uiMessage)
            }
        }else {
            val uiMessage =  UiMessage.Builder().setMessageId(R.string.please_enter_your_invoice_type).setIsCancelable(true) .build()
            handleUiMessage(uiMessage = uiMessage)
        }

    }
    private fun updateInvoice(invoice: Invoice,invoiceType: String){
        viewModelScope.launch {
            updateInvoiceUseCase.invoke(invoice)
        }
    }
    fun updateButton(invoice: Invoice , isValid : Boolean , invoiceType:String){
        validateInvoiceType(invoiceType)
        if (invoiceTypeLiveData.value.isNullOrEmpty()){
            if (isValid){
                updateInvoice(invoice,invoiceType)
                val uiMessage =  UiMessage.Builder().setMessageId(R.string.invoice_updated_correctly).build()
                handleUiMessage(uiMessage = uiMessage)
                Log.d("uiMessage",uiMessage.message.toString())

            }else {
                val uiMessage =  UiMessage.Builder().setMessageId(R.string.invalid_invoice).setIsCancelable(true) .build()
                handleUiMessage(uiMessage = uiMessage)
            }
        }else {
            val uiMessage =  UiMessage.Builder().setMessageId(R.string.please_enter_your_invoice_type).setIsCancelable(true) .build()
            handleUiMessage(uiMessage = uiMessage)
        }

    }

    fun getCategories(){
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect{categories->
                _categoriesLiveData.value=categories

            }
        }

    }
}