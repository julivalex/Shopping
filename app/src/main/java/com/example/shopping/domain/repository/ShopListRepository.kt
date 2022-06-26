package com.example.shopping.domain.repository

import com.example.shopping.domain.models.ShopItem

interface ShopListRepository {

    fun addShopItem(item: ShopItem)

    fun deleteShopItem(item: ShopItem)

    fun editShopItem(item: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>
}