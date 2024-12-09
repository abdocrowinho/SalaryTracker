package com.example.data.dataSources.localDataBase.typeConverters

import androidx.room.TypeConverter
import com.example.data.dataSources.localDataBase.Entity.PurchasedItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PurchasesTypeConverter {
    @TypeConverter
    fun purchasesToJson(value: List<PurchasedItem?>?): String? {
        val gson = Gson()
        return gson.toJson(value)
    }
@TypeConverter
    fun purchasesFromJson(value: String):List<PurchasedItem?>  {
        val listType = object : TypeToken<List<PurchasedItem>>() {}.type
        return Gson().fromJson(value, listType)
    }
}