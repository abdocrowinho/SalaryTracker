package com.example.domain.repos

import com.example.domain.entitys.MostPurchased
import com.example.domain.entitys.PurchasedItem
import kotlinx.coroutines.flow.Flow

interface PurchasedRepo {
  suspend  fun insertPurchased(purchasedItem:List<PurchasedItem?>? )
  fun getMostPurchasedItem():Flow<List<MostPurchased?>?>
}