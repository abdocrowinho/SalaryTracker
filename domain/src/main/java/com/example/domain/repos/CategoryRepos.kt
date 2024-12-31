package com.example.domain.repos

import androidx.lifecycle.LiveData
import com.example.domain.entitys.Category
import com.example.domain.entitys.CategoryStats
import com.example.domain.entitys.Invoice
import kotlinx.coroutines.flow.Flow

interface CategoryRepos {
    suspend fun getCategoryStats(userId:Int):List<CategoryStats?>?
    suspend fun addInvoiceToCategory(invoice: Invoice, categoryName:String)
    fun getCategoriesName():Flow<List<String?>?>
}