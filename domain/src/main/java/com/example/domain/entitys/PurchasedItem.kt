package com.example.domain.entitys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PurchasedItem(
    var name: String?=null,
    var price: Double?=null
):Parcelable