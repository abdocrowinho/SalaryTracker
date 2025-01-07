package com.example.msareefapp.Ui.Fragments.Inovice


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.Invoice
import com.example.domain.useCases.InvoicesUseCases.DeleteInvoiceUseCase
import com.example.domain.useCases.StaticsUseCases.GetUserUseCase
import com.example.domain.useCases.userUseCase.UpdateUserUseCase
import com.example.msareefapp.Bases.BaseViewModel
import com.example.msareefapp.Bases.UiMessage
import com.example.msareefapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val deleteInvoiceUseCase: DeleteInvoiceUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel() {

    private fun deleteInvoice(invoice: Invoice) {
        viewModelScope.launch {
            deleteInvoiceUseCase.invoke(invoice)
        }
    }

    fun deleteButton(negButton: () -> Unit, invoice: Invoice) {
        viewModelScope.launch {

            val userBeforeDelete = getUserUseCase.invoke()
            Log.d("user before delete ", userBeforeDelete.toString())
            val uiMessage = UiMessage.Builder().setMessageId(R.string.are_you_sure_about_delete_this_invoice).setNegTextId(

                R.string.cancel
            ).setPosTextId(R.string.yes_iam_sure).setNegClickListener {
                negButton()
            }.setPosClickListener {
                deleteInvoice(invoice)
                runBlocking {


                    val remainingAfterDelete =
                        invoice.amount?.let { userBeforeDelete!!.remainingSalary?.toDouble()?.plus(it) }
                    updateUserUseCase.invoke(userBeforeDelete!!.copy(remainingSalary =remainingAfterDelete.toString()))
                    val userAfterDelete = getUserUseCase.invoke()
                    Log.d("user after delete ", userBeforeDelete.toString())
                    Log.d("remaining after delete ", remainingAfterDelete.toString())


                }


            }.build()
            handleUiMessage(uiMessage)
        }
        }


}