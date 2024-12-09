package com.example.domain.repos

import com.example.domain.entitys.User
import dagger.Provides

interface UserRepos {
    suspend fun getUser():User?

    suspend fun insertUser(userEntity: User)

    suspend fun deleteUSer(userEntity: User)

    suspend fun updateUser(user:User)
}