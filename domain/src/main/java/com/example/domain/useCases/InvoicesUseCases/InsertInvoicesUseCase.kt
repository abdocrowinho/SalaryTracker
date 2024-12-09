package com.example.domain.useCases.InvoicesUseCases

import com.example.domain.entitys.Invoice
import com.example.domain.repos.InvoiceRepos
import javax.inject.Inject

class InsertInvoicesUseCase @Inject constructor(
   private val repos: InvoiceRepos
) {
    suspend fun invoke(invoice: Invoice){
        repos.insertInvoice(invoice)
    }
}