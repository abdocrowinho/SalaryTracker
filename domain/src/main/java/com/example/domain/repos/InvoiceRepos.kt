package com.example.domain.repos

import com.example.domain.entitys.Invoice

interface InvoiceRepos {
    suspend fun getAllInvoice():List<Invoice?>?

    suspend fun getInvoiceByDate(invoiceDate:Long):List<Invoice?>?

    suspend fun insertInvoice(invoice: Invoice)

    suspend fun updateInvoice(invoice: Invoice)

    suspend fun deleteInvoice(invoice: Invoice)
}