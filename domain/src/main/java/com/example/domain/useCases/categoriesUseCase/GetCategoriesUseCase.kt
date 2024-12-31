package com.example.domain.useCases.categoriesUseCase

import com.example.domain.entitys.Category
import com.example.domain.repos.CategoryRepos
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepos: CategoryRepos
) {
    fun invoke():Flow<List<String?>?>{
        return categoryRepos.getCategoriesName()
    }
}