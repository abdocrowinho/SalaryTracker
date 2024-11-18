package com.example.data

import com.example.data.dataSources.localDataBase.Entity.InvoiceEntity
import com.example.data.dataSources.localDataBase.Entity.PurchasedItem
import com.example.data.dataSources.localDataBase.Entity.UserEntity
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
        userId = userId?:0,
        userName = userName,
        salary = salary
    )


}

fun Invoice.toInvoiceEntity(): InvoiceEntity {
    return InvoiceEntity(
        id = id!!,
        purchasedItems = purchasedItems?.map { it?.toPurchasedItem() },
        time = time,
        dateTime = dateTime
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
        userId = userId,
        userName = userName,
        salary = salary
    )


}

fun InvoiceEntity.toInvoice(): Invoice {
    return Invoice(
        id = id,
        purchasedItems = purchasedItems?.map { it?.toPurchasedItem() },
        time = time,
        dateTime = dateTime
    )
}

fun PurchasedItem.toPurchasedItem(): com.example.domain.entitys.PurchasedItem {
    return com.example.domain.entitys.PurchasedItem(name = name, price = price)
}
/*---------------------------data to domain-----------------------------*/


