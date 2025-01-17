package com.example.data.dataSources.localDataBase.typeConverters

import androidx.room.TypeConverter
import com.example.data.dataSources.localDataBase.Entity.UserEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserEntityToTypeConverter {
    @TypeConverter
    fun userEntityToJson(value: List<UserEntity?>?): String? {
        val gson = Gson()
        return gson.toJson(value)
    }
@TypeConverter
    fun userEntityFromJson(value: String): UserEntity? {
        val listType = object : TypeToken<List<UserEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }
}