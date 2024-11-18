package com.example.data.dataSources.localDataBase.typeConverters

import androidx.room.TypeConverter
import com.example.data.dataSources.localDataBase.Entity.InvoiceEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class InvoiceTypeConverters {
@TypeConverter
    fun invoiceToJson(value:List<InvoiceEntity>?):String?{
        val json = Gson()
        return json.toJson(value)
    }
@TypeConverter
fun invoiceFromJson(value: String?):List<InvoiceEntity?>?{
    val listType= object :TypeToken<List<InvoiceEntity>>() {}.type
    return Gson().fromJson(value,listType)

}
}