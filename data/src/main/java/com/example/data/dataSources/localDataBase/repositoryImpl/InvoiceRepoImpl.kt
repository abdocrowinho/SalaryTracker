package com.example.data.dataSources.localDataBase.repositoryImpl

import com.example.data.dataSources.localDataBase.Dao.InvoiceDao
import com.example.data.toInvoice
import com.example.data.toInvoiceEntity
import com.example.domain.entitys.Invoice
import com.example.domain.repos.InvoiceRepos
import javax.inject.Inject

class InvoiceRepoImpl @Inject constructor(
    private val invoiceDao: InvoiceDao

) : InvoiceRepos {
    override suspend fun getAllInvoice(): List<Invoice?>? {
        return invoiceDao.getAllInvoice()?.map { it?.toInvoice() }
    }

    override suspend fun getInvoiceByDate(invoiceDate: Long): List<Invoice?>? {
        return invoiceDao.getInvoiceByDate(invoiceDate)?.map { it?.toInvoice() }
    }

    override suspend fun insertInvoice(invoice: Invoice) {
        invoiceDao.insertInvoice(
            invoice.toInvoiceEntity()
        )
    }

    override suspend fun updateInvoice(invoice: Invoice) {
        invoiceDao.updateInvoice(
            invoice.toInvoiceEntity()
        )
    }

    override suspend fun deleteInvoice(invoice: Invoice) {
        invoiceDao.deleteInvoice(invoice.toInvoiceEntity())
    }
}