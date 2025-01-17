package com.example.domain.useCases.InvoicesUseCases

import com.example.domain.entitys.PurchasedItem
import com.example.domain.repos.PurchasedRepo
import javax.inject.Inject

class InsertPurchasedItemUseCase @Inject constructor(
    val purchasedRepo: PurchasedRepo
) {
  suspend  fun invoke(items : List<PurchasedItem?>?){
        purchasedRepo.insertPurchased(items)
    }
}