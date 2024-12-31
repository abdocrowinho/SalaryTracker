package com.example.domain.useCases.StaticsUseCases

import com.example.domain.entitys.CategoryStats
import com.example.domain.repos.CategoryRepos
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val repo : CategoryRepos
) {
  suspend  fun invoke(userId:Int):List<CategoryStats?>?{
      return  repo.getCategoryStats(userId)
    }
}