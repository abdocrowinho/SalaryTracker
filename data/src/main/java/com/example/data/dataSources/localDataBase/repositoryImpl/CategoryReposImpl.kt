package com.example.data.dataSources.localDataBase.repositoryImpl

import android.util.Log
import com.example.data.dataSources.localDataBase.Dao.CategoryDao
import com.example.data.dataSources.localDataBase.Dao.InvoiceDao
import com.example.data.dataSources.localDataBase.Dao.UserDao
import com.example.data.dataSources.localDataBase.Entity.CategoryEntity
import com.example.data.dataSources.localDataBase.Entity.InvoiceEntity
import com.example.data.dataSources.localDataBase.Entity.UserEntity
import com.example.data.toCategoryEntity
import com.example.data.toCategoryStats
import com.example.data.toInvoiceEntity
import com.example.domain.entitys.Category
import com.example.domain.entitys.CategoryStats
import com.example.domain.entitys.Invoice
import com.example.domain.repos.CategoryRepos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryReposImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val invoiceDao: InvoiceDao,
    private val userDao: UserDao
) : CategoryRepos {
    override suspend fun getCategoryStats(userId: Int): List<CategoryStats?>? {
        return categoryDao.getCategoryStats(userId)?.map { it?.toCategoryStats() }
    }

    override suspend fun addInvoiceToCategory(invoice: Invoice, categoryName: String) {
        val category = categoryDao.searchOnCategoryByName(categoryName)
        if (category == null) {
            addInvoiceWithCategory(invoice,categoryName)
        } else {
            addInvoiceWithoutCategory(category,invoice.toInvoiceEntity())
        }
        val currentUser = userDao.getUser()
        Log.d("userRemaining before is","$currentUser")
        updateUserRemaining(currentUser , invoice.amount)

    }

    override fun getCategoriesName(): Flow<List<String?>?> {
      return  categoryDao.getCategoriesName()
      }



    private suspend fun updateUserRemaining(currentUser: UserEntity?, amount: Double?) {
        Log.d("updateUserRemaining","is Logged")
        Log.d("Invoice Amount is ","$amount")
        Log.d("user is ","$currentUser")

        userDao.updateUser(
            UserEntity(
                id = currentUser!!.id,
                userName = currentUser.userName,
                remainingSalary = currentUser.remainingSalary?.toDouble()?.minus(amount!!).toString()
                , salary = currentUser.salary
                , invoices = currentUser.invoices
                , notification =currentUser.notification
                , expectedSavings = currentUser.expectedSavings
            )
        )
        val newUser = userDao.getUser()

        Log.d("userRemaining after is ","${newUser!!.remainingSalary}")

    }

    private suspend fun addInvoiceWithCategory(invoice: Invoice, categoryName: String) {
        val newCategoryID =
            categoryDao.insertCategory(Category(name = categoryName).toCategoryEntity())
        val newInvoice = invoice.copy(categoryId = newCategoryID)
        invoiceDao.insertInvoice(newInvoice.toInvoiceEntity())
    }

    private suspend fun  addInvoiceWithoutCategory(category: CategoryEntity, invoice: InvoiceEntity) {
        val oldCategory = category.id
        val newInvoiceWithOldCategory = invoice.copy(categoryId = oldCategory)
        invoiceDao.insertInvoice(newInvoiceWithOldCategory)
    }
}