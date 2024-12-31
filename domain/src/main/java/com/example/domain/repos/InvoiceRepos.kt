package com.example.domain.repos

import com.example.domain.entitys.Invoice
import kotlinx.coroutines.flow.Flow

interface InvoiceRepos {
     fun getAllInvoice():Flow< List<Invoice?>?>

    suspend fun getInvoiceByDate(invoiceDate:Long):List<Invoice?>?

    suspend fun insertInvoice(invoice: Invoice)

    suspend fun updateInvoice(invoice: Invoice)

    suspend fun deleteInvoice(invoice: Invoice)
}