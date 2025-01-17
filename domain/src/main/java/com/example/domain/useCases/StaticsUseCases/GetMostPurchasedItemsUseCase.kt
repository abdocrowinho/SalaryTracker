package com.example.domain.useCases.StaticsUseCases

import com.example.domain.entitys.MostPurchased
import com.example.domain.repos.PurchasedRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMostPurchasedItemsUseCase @Inject constructor(
  private  val repo: PurchasedRepo
) {
    fun invoke():Flow<List<MostPurchased?>?>{
        return repo.getMostPurchasedItem()
    }
}