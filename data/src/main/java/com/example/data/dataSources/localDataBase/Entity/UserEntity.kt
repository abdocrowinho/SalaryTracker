package com.example.data.dataSources.localDataBase.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.Constants
import com.example.data.dataSources.localDataBase.typeConverters.InvoiceTypeConverters


@Entity(tableName = Constants.USER_ENTITY)
@TypeConverters(InvoiceTypeConverters::class)
data class UserEntity(
    @PrimaryKey(true)
    val id: Int = 0,
    val userName: String? = null,
    val salary: String? = null,
    val remainingSalary:String ?=salary,
    val notification: String? = null,
    val expectedSavings: String? = null,
    val invoices: List<InvoiceEntity?>?= ArrayList<InvoiceEntity>()

)

