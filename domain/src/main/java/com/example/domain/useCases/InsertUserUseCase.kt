package com.example.domain.useCases

import com.example.domain.entitys.User
import com.example.domain.repos.UserRepos
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val userRepos: UserRepos
) {
  suspend  fun invoke (user: User){
        userRepos.insertUser(user)
    }
}