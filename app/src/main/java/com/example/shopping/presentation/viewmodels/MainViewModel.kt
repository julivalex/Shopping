package com.example.shopping.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.repository.ShopListRepositoryImpl
import com.example.shopping.domain.models.ShopItem
import com.example.shopping.domain.usecases.DeleteShopItemUseCase
import com.example.shopping.domain.usecases.EditShopItemUseCase
import com.example.shopping.domain.usecases.GetShopListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(item: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(item)
        }
    }

    fun changeEnabledState(item: ShopItem) {
        viewModelScope.launch {
            val newItem = item.copy(enabled = !item.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}