package com.example.data.dataSources.localDataBase.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int =0,
    val name : String ?=null
)