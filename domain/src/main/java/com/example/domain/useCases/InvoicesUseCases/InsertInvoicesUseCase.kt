package com.example.domain.useCases.InvoicesUseCases

import com.example.domain.entitys.Invoice
import com.example.domain.repos.CategoryRepos
import javax.inject.Inject

class InsertInvoicesUseCase @Inject constructor(
   private val repos: CategoryRepos

) {
    suspend fun invoke(invoice: Invoice , categoryName : String){
        repos.addInvoiceToCategory(invoice,categoryName)
    }
}