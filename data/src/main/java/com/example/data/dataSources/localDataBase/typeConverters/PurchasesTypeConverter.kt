package com.example.data.dataSources.localDataBase.typeConverters

import androidx.room.TypeConverter
import com.example.data.dataSources.localDataBase.Entity.PurchasedItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PurchasesTypeConverter {
    @TypeConverter
    fun purchasesToJson(value: List<PurchasedItemEntity?>?): String? {
        val gson = Gson()
        return gson.toJson(value)
    }
@TypeConverter
    fun purchasesFromJson(value: String):List<PurchasedItemEntity?>  {
        val listType = object : TypeToken<List<PurchasedItemEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }
}