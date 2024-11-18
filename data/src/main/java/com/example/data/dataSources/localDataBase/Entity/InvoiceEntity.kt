package com.example.data.dataSources.localDataBase.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.dataSources.localDataBase.typeConverters.PurchasesTypeConverter


@Entity("Invoice")
@TypeConverters(PurchasesTypeConverter::class)

data class InvoiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val purchasedItems: List<PurchasedItem?>? = ArrayList<PurchasedItem>(),
    val dateTime: Long? = null,
    val time: Long? = null

)
