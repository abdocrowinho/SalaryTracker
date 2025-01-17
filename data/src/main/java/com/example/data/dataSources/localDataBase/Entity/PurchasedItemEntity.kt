package com.example.data.dataSources.localDataBase.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Purchases", foreignKeys = [
    ForeignKey(
        entity = InvoiceEntity::class,
        parentColumns = ["id"],
        childColumns = ["invoiceId"],
        onDelete = ForeignKey.CASCADE
    )
]
)
data class PurchasedItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int ?=0,
    val name: String?=null,
    val price: Double?=null,
    val invoiceId: Int ?= null
)
