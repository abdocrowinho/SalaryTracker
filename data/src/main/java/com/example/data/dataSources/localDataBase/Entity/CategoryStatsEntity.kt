package com.example.data.dataSources.localDataBase.Entity

data class CategoryStatsEntity(
    val id : Int ?=0,
    val name : String ?= "another" ,
    val numberOfInvoices : Int ?=0,
    val totalExpenses : Double ?=0.0,
    val salary : Double
)