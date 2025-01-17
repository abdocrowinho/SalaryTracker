package com.example.data.dataSources.localDataBase.repositoryImpl

import android.util.Log
import com.example.data.dataSources.localDataBase.Dao.CategoryDao
import com.example.data.dataSources.localDataBase.Dao.InvoiceDao
import com.example.data.dataSources.localDataBase.Dao.PurchasedDao
import com.example.data.toDaySpending
import com.example.data.toInvoice
import com.example.data.toInvoiceEntity
import com.example.data.toPurchasedItem
import com.example.domain.entitys.DaySpending
import com.example.domain.entitys.Invoice
import com.example.domain.repos.InvoiceRepos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class InvoiceRepoImpl @Inject constructor(
    private val invoiceDao: InvoiceDao,
    private val categoryDao: CategoryDao,
    private val purchasedDao: PurchasedDao

) : InvoiceRepos {
    override fun getAllInvoice(): Flow<List<Invoice?>?> {
        return invoiceDao.getAllInvoice().map { invoices ->
            invoices?.map { it?.toInvoice() }
        }
    }

    override suspend fun getInvoiceByDate(invoiceDate: Long): List<Invoice?>? {
        return invoiceDao.getInvoiceByDate(invoiceDate)?.map { it?.toInvoice() }
    }

    override fun getInvoicesByCategoryId(categoryId: String): Flow<List<Invoice>> {
        return invoiceDao.getInvoiceByCategory(categoryId).map { invoices ->
            invoices.map { it.toInvoice() }
        }
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
        val category = invoice.categoryId?.let { categoryDao.getCategoryById(it) }
        val invoicesCategoryList =
            invoiceDao.getInvoiceByCategory(invoice.categoryId.toString()).first()

        if (invoicesCategoryList.size == 1) {
            invoiceDao.deleteInvoice(invoice.toInvoiceEntity())
            category?.let { it1 -> categoryDao.deleteCategory(it1) }
        } else {
            invoiceDao.deleteInvoice(invoice.toInvoiceEntity())

        }
    }

    override fun getSpendingByDay(): Flow<List<DaySpending?>?> {
        return invoiceDao.getSpendingByDay().map { daySpending ->
            Log.d("getSpendingByDay", "Raw data from repo: $daySpending")

            val result = daySpending?.map { it?.toDaySpending() }

            Log.d("getSpendingByDay", "Mapped data: $result")

            result
        }


    }
}