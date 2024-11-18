package com.example.data.dataSources.localDataBase.repositoryImpl

import com.example.data.dataSources.localDataBase.Dao.UserDao
import com.example.data.toUser
import com.example.data.toUserEntity
import com.example.domain.entitys.User
import com.example.domain.repos.UserRepos
import javax.inject.Inject

class UserReposImpl @Inject constructor(

    private val userDao: UserDao

) : UserRepos  {
    override suspend fun getUser(): User {
     return   userDao.getUser().toUser()
    }

    override suspend fun insertUser(userEntity: User) {
        return userDao.insertUser(userEntity.toUserEntity())
    }

    override suspend fun deleteUSer(userEntity: User) {
        userDao.deleteUSer(userEntity.toUserEntity())
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user.toUserEntity())
    }
}