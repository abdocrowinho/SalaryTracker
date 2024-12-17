package com.example.domain.useCases.StaticsUseCases

import com.example.domain.entitys.User
import com.example.domain.repos.UserRepos
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repo : UserRepos
) {
   suspend fun invoke():User?{
       return repo.getUser()
    }

}