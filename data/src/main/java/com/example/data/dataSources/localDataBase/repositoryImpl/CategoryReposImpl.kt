package com.example.data.dataSources.localDataBase.repositoryImpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.dataSources.localDataBase.Dao.CategoryDao
import com.example.data.toCategoryStats
import com.example.domain.entitys.CategoryStats
import com.example.domain.repos.CategoryRepos
import javax.inject.Inject

class CategoryReposImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepos {
    override suspend fun getCategoryStats(userId:Int): List<CategoryStats?>? {
        return categoryDao.getCategoryStats(userId)?.map { it?.toCategoryStats() }
    }
}