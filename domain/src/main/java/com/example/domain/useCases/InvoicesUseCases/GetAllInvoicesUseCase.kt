package com.example.domain.useCases.InvoicesUseCases

import com.example.domain.entitys.Invoice
import com.example.domain.repos.InvoiceRepos
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllInvoicesUseCase @Inject constructor(
    private val invoiceRepos: InvoiceRepos
){
    fun invoke():Flow< List<Invoice?>?>{
     return  invoiceRepos.getAllInvoice()
   }
}
