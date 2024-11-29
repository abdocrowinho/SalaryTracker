package com.example.domain.repos

import androidx.lifecycle.LiveData
import com.example.domain.entitys.CategoryStats

interface CategoryRepos {
    suspend fun getCategoryStats(userId:Int):List<CategoryStats?>?
}