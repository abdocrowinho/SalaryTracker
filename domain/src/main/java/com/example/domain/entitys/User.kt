package com.example.domain.entitys

data class User(
    val userId:Int?=null,
    val userName: String? = null,
    val salary: String? = null,
    val notification: String?=null,
    val expectedSavings:String?=null,
    val invoices: List<Invoice?>?=null

)



