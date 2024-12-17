package com.example.data.dataSources.localDataBase.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.dataSources.localDataBase.Entity.CategoryEntity
import com.example.data.dataSources.localDataBase.Entity.CategoryStatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query(
        """
    SELECT c.id AS id,c.name AS name,
    COUNT(i.id)AS numberOfInvoices,
    SUM(i.amount) AS totalExpenses,
    u.salary AS salary
    FROM Category c 
    LEFT JOIN Invoice i ON c.id = i.categoryId
    CROSS JOIN User u
    WHERE u.id = :userId
    GROUP BY c.id, u.salary
    
    """
    )


  suspend  fun getCategoryStats(userId: Int): List<CategoryStatsEntity?>?
  @Query("Select name from category")
    fun getCategoriesName():Flow<List<String?>?>
  @Query("Select * from category where name =:categoryName Limit 1")
 suspend fun  searchOnCategoryByName(categoryName : String):CategoryEntity?

  @Insert(onConflict = OnConflictStrategy.IGNORE)
 suspend fun insertCategory(category : CategoryEntity):Long
}


