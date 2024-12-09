package com.example.data.dataSources.localDataBase.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.dataSources.localDataBase.typeConverters.PurchasesTypeConverter


@Entity(
    "invoice", foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            childColumns = ["categoryId"],
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@TypeConverters(PurchasesTypeConverter::class)

data class InvoiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val purchasedItems: List<PurchasedItem?>? = ArrayList<PurchasedItem>(),
    val dateTime: String? = null,
    val time: String? = null,
    val categoryId: Int ?= null,
    val amount: Double? = 0.0,
)
