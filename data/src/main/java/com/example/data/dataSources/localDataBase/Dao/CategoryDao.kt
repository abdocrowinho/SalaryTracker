package com.example.data.dataSources.localDataBase.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.data.dataSources.localDataBase.Entity.CategoryStatsEntity

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
}


