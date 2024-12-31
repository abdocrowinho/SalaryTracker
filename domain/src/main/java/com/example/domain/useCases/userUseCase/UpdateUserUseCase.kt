package com.example.domain.useCases.userUseCase

import com.example.domain.entitys.User
import com.example.domain.repos.UserRepos
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
   private val userRepos: UserRepos
) {
    suspend fun invoke(newUser:User){
        userRepos.updateUser(newUser)
    }
}