package com.example.domain.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PurchasedItem(
    val id : Int ?=null,
    var name: String?=null,
    var price: Double?=null,
    val invoiceId: Int ?= null
):Parcelable