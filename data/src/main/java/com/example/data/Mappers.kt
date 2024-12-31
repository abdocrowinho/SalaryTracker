package com.example.data

import com.example.data.dataSources.localDataBase.Entity.CategoryEntity
import com.example.data.dataSources.localDataBase.Entity.CategoryStatsEntity
import com.example.data.dataSources.localDataBase.Entity.InvoiceEntity
import com.example.data.dataSources.localDataBase.Entity.PurchasedItem
import com.example.data.dataSources.localDataBase.Entity.UserEntity
import com.example.domain.entitys.Category
import com.example.domain.entitys.CategoryStats
import com.example.domain.entitys.Invoice
import com.example.domain.entitys.User

/*-----------------------------domain to data---------------------------*/

fun com.example.domain.entitys.PurchasedItem.toPurchasedItem(): PurchasedItem {
    return PurchasedItem(name = name, price = price)
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        expectedSavings = expectedSavings,
        invoices = invoices?.map { it?.toInvoiceEntity() },
        notification = notification,
        id = userId?:0,
        userName = userName,
        salary = salary,
        remainingSalary = remainingSalary

    )


}

fun Invoice.toInvoiceEntity(): InvoiceEntity {
    return InvoiceEntity(
        id = id?:0,
        purchasedItems = purchasedItems?.map { it?.toPurchasedItem() },
        time = time,
        dateTime = dateTime,
        categoryId = categoryId,
        amount = amount


    )
}
fun CategoryStats.toCategoryStatsEntity(): CategoryStatsEntity{
    return CategoryStatsEntity(
        id = id
        , name = name
        , totalExpenses = totalExpenses
        , numberOfInvoices = numberOfInvoices
        , salary = salary
    )
}

fun Category.toCategoryEntity():CategoryEntity {
    return CategoryEntity(
        id = id!!,
        name = name
    )
}

/*---------------------------domain to data-----------------------------*/


/*----------------------------------------------------------------------*/


/*---------------------------data to domain-----------------------------*/

fun UserEntity.toUser(): User {
    return User(
        expectedSavings = expectedSavings,
        invoices = invoices?.map { it?.toInvoice() },
        notification = notification,
        userId = id,
        userName = userName,
        salary = salary,
        remainingSalary = remainingSalary

    )


}

fun InvoiceEntity.toInvoice(): Invoice {
    return Invoice(
        id = id,
        purchasedItems = purchasedItems?.map { it?.toPurchasedItem() },
        time = time,
        dateTime = dateTime,
        categoryId = categoryId,
        amount = amount

    )
}

fun PurchasedItem.toPurchasedItem(): com.example.domain.entitys.PurchasedItem {
    return com.example.domain.entitys.PurchasedItem(name = name, price = price)
}
fun CategoryStatsEntity.toCategoryStats(): CategoryStats{
    return CategoryStats(
        id = id
        , name = name
        , totalExpenses = totalExpenses
        , numberOfInvoices = numberOfInvoices,
        salary = salary
    )
}
fun CategoryEntity.toCategoryEntity():Category {
    return Category(
        id = id,
        name = name
    )
}

/*---------------------------data to domain-----------------------------*/


