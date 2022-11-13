package com.example.shopping.di

import androidx.lifecycle.ViewModel
import com.example.shopping.presentation.viewmodels.MainViewModel
import com.example.shopping.presentation.viewmodels.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(model: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    fun bindShopItemViewModel(model: ShopItemViewModel): ViewModel
}