package com.example.domain.entitys

import java.time.temporal.TemporalAmount


data class Invoice(
    val id : Int ?=null ,
    val purchasedItems:List<PurchasedItem?>?=null,
    val dateTime:String?=null,
    val time : String?=null,
    val categoryId : Int?=null,
    val amount: Double ?=0.0

)
