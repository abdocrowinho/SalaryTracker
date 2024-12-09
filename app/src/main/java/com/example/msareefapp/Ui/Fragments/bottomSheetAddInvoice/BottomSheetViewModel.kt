package com.example.msareefapp.Ui.Fragments.bottomSheetAddInvoice

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.Invoice
import com.example.domain.entitys.PurchasedItem
import com.example.domain.useCases.InvoicesUseCases.InsertInvoicesUseCase
import com.example.msareefapp.Bases.BaseValidation.FieldsValidation
import com.example.msareefapp.Bases.BaseValidation.MinLengthValidator
import com.example.msareefapp.Bases.BaseValidation.NotEmptyValidator
import com.example.msareefapp.Bases.BaseViewModel
import com.example.msareefapp.Bases.UiMessage
import com.example.msareefapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(

    private val insertInvoiceUseCase : InsertInvoicesUseCase
) : BaseViewModel(){
  private  var _invoiceTypeLiveData = MutableLiveData<List<String?>?>()
    val invoiceTypeLiveData : LiveData<List<String?>?>get() = _invoiceTypeLiveData

    private fun insertInvoice(invoice : Invoice){
        viewModelScope.launch {
            insertInvoiceUseCase.invoke(invoice)
        }
    }
    private fun validateInvoiceType(invoiceType: String) {
        val validator = listOf(NotEmptyValidator(), MinLengthValidator(5))
        _invoiceTypeLiveData.value =
            FieldsValidation.validateFields(validators = validator, value = invoiceType)
    }
    fun doneButton(isValid : Boolean,invoice: Invoice ,invoiceType: String ){
        validateInvoiceType(invoiceType)
        if (invoiceTypeLiveData.value.isNullOrEmpty()){
            if (isValid){
                insertInvoice(invoice)
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
}