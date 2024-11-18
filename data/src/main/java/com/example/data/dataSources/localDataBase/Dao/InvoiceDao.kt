package com.example.data.dataSources.localDataBase.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.Constants
import com.example.data.dataSources.localDataBase.Entity.InvoiceEntity

@Dao
interface InvoiceDao {
    @Query("Select * From Invoice")
    suspend fun getAllInvoice():List<InvoiceEntity?>?

    @Query("Select * From Invoice Where ${Constants.INVOICE_DATE_TIME}=:invoiceDate ")
    suspend fun getInvoiceByDate(invoiceDate:Long):List<InvoiceEntity?>?

    @Insert
    suspend fun insertInvoice(invoice: InvoiceEntity)

    @Update
    suspend fun updateInvoice(invoice: InvoiceEntity)

    @Delete
    suspend fun deleteInvoice(invoice: InvoiceEntity)

}