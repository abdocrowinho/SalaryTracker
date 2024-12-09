package com.example.msareefapp.Ui.Fragments.Inovice

import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.Invoice
import com.example.domain.repos.InvoiceRepos
import com.example.domain.useCases.InvoicesUseCases.InsertInvoicesUseCase
import com.example.msareefapp.Bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val insertInvoicesUseCase: InsertInvoicesUseCase
) : BaseViewModel() {

    fun insertInvoice(invoice: Invoice){
        viewModelScope.launch(Dispatchers.IO) {
            insertInvoicesUseCase.invoke(invoice)
        }
    }

}