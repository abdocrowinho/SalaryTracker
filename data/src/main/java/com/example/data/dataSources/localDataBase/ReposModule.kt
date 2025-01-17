package com.example.data.dataSources.localDataBase

import com.example.data.dataSources.localDataBase.repositoryImpl.CategoryReposImpl
import com.example.data.dataSources.localDataBase.repositoryImpl.InvoiceRepoImpl
import com.example.data.dataSources.localDataBase.repositoryImpl.PurchasedRepoImpl
import com.example.data.dataSources.localDataBase.repositoryImpl.UserReposImpl
import com.example.domain.repos.CategoryRepos
import com.example.domain.repos.InvoiceRepos
import com.example.domain.repos.PurchasedRepo
import com.example.domain.repos.UserRepos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 object ReposModule{
    @Provides
    @Singleton
    fun providesUserRepos(
      userReposImpl: UserReposImpl
  ):UserRepos =userReposImpl


        @Singleton
        @Provides
        fun providesInvoiceRepos(
            invoiceRepoImpl: InvoiceRepoImpl
        ): InvoiceRepos =invoiceRepoImpl

    @Provides
    @Singleton
    fun providesCategoriesRepos(
        categoryReposImpl: CategoryReposImpl
    ):CategoryRepos =categoryReposImpl

    @Provides
    @Singleton
    fun providesPurchasedRepos(
        purchasedRepoImpl: PurchasedRepoImpl
    ) : PurchasedRepo = purchasedRepoImpl

}
