package com.example.shopping.di

import android.app.Application
import com.example.shopping.data.db.AppDatabase
import com.example.shopping.data.db.ShopListDao
import com.example.shopping.data.repository.ShopListRepositoryImpl
import com.example.shopping.domain.repository.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindShopListRepository(repository: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideShopListDao(application: Application): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}