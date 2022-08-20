package com.example.shopping.domain.repository

import androidx.lifecycle.LiveData
import com.example.shopping.domain.models.ShopItem

interface ShopListRepository {

    suspend fun addShopItem(item: ShopItem)

    suspend fun deleteShopItem(item: ShopItem)

    suspend fun editShopItem(item: ShopItem)

    suspend fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}