package com.example.data.dataSources.localDataBase.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.dataSources.localDataBase.Entity.MostPurchasedEntity
import com.example.data.dataSources.localDataBase.Entity.PurchasedItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchasedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertPurchased(purchasedItem:List<PurchasedItemEntity>)
@Query("select name ,count(name) as number , sum(price) as total from Purchases group by name order by  number desc")
fun getMost5PurchasedItem () : Flow<List<MostPurchasedEntity?>?>
}