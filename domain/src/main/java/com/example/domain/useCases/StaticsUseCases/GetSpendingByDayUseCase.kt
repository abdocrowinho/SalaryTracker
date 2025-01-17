package com.example.domain.useCases.StaticsUseCases

import com.example.domain.entitys.DaySpending
import com.example.domain.repos.InvoiceRepos
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpendingByDayUseCase @Inject constructor(

    private val invoiceRepos: InvoiceRepos
) {
    fun invoke () : Flow<List<DaySpending?>?>{
        return invoiceRepos.getSpendingByDay()
    }
}