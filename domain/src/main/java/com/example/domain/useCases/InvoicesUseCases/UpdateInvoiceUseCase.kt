package com.example.domain.useCases.InvoicesUseCases

import com.example.domain.entitys.Invoice
import com.example.domain.repos.InvoiceRepos
import javax.inject.Inject

class UpdateInvoiceUseCase @Inject constructor(
    val repos: InvoiceRepos
) {
    suspend fun invoke(invoice: Invoice) {
        repos.updateInvoice(invoice)
    }
}