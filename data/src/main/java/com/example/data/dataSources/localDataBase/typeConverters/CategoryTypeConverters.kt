package com.example.data.dataSources.localDataBase.typeConverters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.domain.entitys.CategoryStats
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryTypeConverters {
    @TypeConverter
    fun fromCategoryToString (value : List<CategoryStats>):String? {
        val gson =Gson()
        return gson.toJson(value)
    }
    @TypeConverter
    fun fromStringToJson(value : String?):List<CategoryStats?>?{
        val  typeList = object : TypeToken<CategoryStats>() {}.type
        return Gson().fromJson(value,typeList)
    }
}