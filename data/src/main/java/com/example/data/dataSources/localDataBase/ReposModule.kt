package com.example.data.dataSources.localDataBase

import com.example.data.dataSources.localDataBase.repositoryImpl.InvoiceRepoImpl
import com.example.data.dataSources.localDataBase.repositoryImpl.UserReposImpl
import com.example.domain.repos.InvoiceRepos
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
    fun userReposBinding(
      userReposImpl: UserReposImpl
  ):UserRepos =userReposImpl


        @Provides
        @Singleton
        fun invoiceReposBinding(
            invoiceRepoImpl: InvoiceRepoImpl
        ): InvoiceRepos =invoiceRepoImpl
    }
