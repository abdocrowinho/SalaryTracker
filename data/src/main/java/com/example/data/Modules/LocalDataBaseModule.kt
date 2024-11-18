package com.example.data.Modules

import android.content.Context
import androidx.room.Room
import com.example.data.Constants
import com.example.data.dataSources.localDataBase.Dao.InvoiceDao
import com.example.data.dataSources.localDataBase.Dao.UserDao
import com.example.data.dataSources.localDataBase.LocalDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):LocalDataBase{
        return Room.databaseBuilder(context = context.applicationContext,
            LocalDataBase::class.java,
            Constants.APP_DATA_BASE_NAME
            ).build()
    }
    @Singleton
    @Provides
    fun provideUserDao(localDataBase: LocalDataBase):UserDao{
        return localDataBase.userDao()
    }
    @Singleton
    @Provides
    fun provideInvoiceDao(localDataBase: LocalDataBase):InvoiceDao{
        return localDataBase.invoiceDao()
    }

}