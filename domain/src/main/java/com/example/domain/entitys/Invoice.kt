package com.example.domain.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.temporal.TemporalAmount

@Parcelize
data class Invoice(
    val id : Int ?=null ,
    val purchasedItems:List<PurchasedItem?>?=null,
    val dateTime:String?=null,
    val time : String?=null,
    val categoryId : Long?=null,
    val amount: Double ?=0.0

):Parcelable
