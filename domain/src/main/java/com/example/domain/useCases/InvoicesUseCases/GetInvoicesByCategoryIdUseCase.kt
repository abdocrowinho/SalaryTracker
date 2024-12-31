package com.example.domain.useCases.InvoicesUseCases

import com.example.domain.entitys.Invoice
import com.example.domain.repos.InvoiceRepos
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInvoicesByCategoryIdUseCase @Inject constructor(
    val repos : InvoiceRepos
) {
    fun invoke(categoryId : String): Flow<List<Invoice>> {
     return   repos.getInvoicesByCategoryId(categoryId)
    }
}