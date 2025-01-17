package com.example.data.dataSources.localDataBase.repositoryImpl

import com.example.data.dataSources.localDataBase.Dao.PurchasedDao
import com.example.data.toMostPurchased
import com.example.data.toPurchasedItem
import com.example.domain.entitys.MostPurchased
import com.example.domain.entitys.PurchasedItem
import com.example.domain.repos.PurchasedRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PurchasedRepoImpl @Inject constructor(
    val purchasedDao : PurchasedDao
) : PurchasedRepo{
    override suspend fun insertPurchased(purchasedItem: List<PurchasedItem?>?) {
        purchasedDao.insertPurchased(purchasedItem!!.map { it!!.toPurchasedItem() })
    }

    override fun getMostPurchasedItem(): Flow<List<MostPurchased?>?> {
       return purchasedDao.getMost5PurchasedItem().map { mostPurchasedList->
           mostPurchasedList?.map { it?.toMostPurchased() }
       }
    }
}