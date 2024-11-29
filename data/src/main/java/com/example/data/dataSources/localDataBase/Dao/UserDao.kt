package com.example.data.dataSources.localDataBase.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.Constants
import com.example.data.dataSources.localDataBase.Entity.UserEntity

@Dao
interface UserDao {
    @Query("Select * From ${Constants.USER_ENTITY} Limit 1 ")
    suspend fun getUser():UserEntity?

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Delete
    suspend fun deleteUSer(userEntity: UserEntity)

    @Update
    suspend fun updateUser(user:UserEntity)
}
