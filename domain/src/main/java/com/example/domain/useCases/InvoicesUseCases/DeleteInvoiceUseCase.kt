package com.example.domain.useCases.InvoicesUseCases

import android.util.Log
import com.example.domain.entitys.Invoice
import com.example.domain.repos.CategoryRepos
import com.example.domain.repos.InvoiceRepos
import com.example.domain.repos.UserRepos
import javax.inject.Inject

class DeleteInvoiceUseCase @Inject constructor(
    val repos: InvoiceRepos,
) {
    suspend fun invoke(invoice: Invoice) {
        repos.deleteInvoice(invoice)
    }
}