package com.example.data.dataSources.localDataBase.Entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.protobuf.Value

@Entity(tableName = "category", indices = [Index(value = ["name"])])
data class CategoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Long =0,
    val name : String ?=null
)