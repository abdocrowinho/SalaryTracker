package com.example.domain.entitys


data class Invoice(
    val id : Int ?=null ,
    val purchasedItems:List<PurchasedItem?>?=null,
    val dateTime:Long?=null,
    val time : Long ?=null,
    val invoiceType : String
)
