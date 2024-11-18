package com.example.data.dataSources.localDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.dataSources.localDataBase.Dao.InvoiceDao
import com.example.data.dataSources.localDataBase.Dao.UserDao
import com.example.data.dataSources.localDataBase.Entity.InvoiceEntity
import com.example.data.dataSources.localDataBase.Entity.UserEntity
import com.example.data.dataSources.localDataBase.typeConverters.InvoiceTypeConverters
import com.example.data.dataSources.localDataBase.typeConverters.PurchasesTypeConverter
import com.example.data.dataSources.localDataBase.typeConverters.UserEntityToTypeConverter

@Database(
    entities = [UserEntity::class, InvoiceEntity::class], version = 1
)
@TypeConverters(
    InvoiceTypeConverters::class,
    UserEntityToTypeConverter::class,
    PurchasesTypeConverter::class
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun invoiceDao(): InvoiceDao
}