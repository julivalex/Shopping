package com.example.shopping.data.repository

import com.example.shopping.domain.models.ShopItem
import com.example.shopping.domain.repository.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl : ShopListRepository {

    private var autoIncrementId = 0
    private val shopList = mutableListOf<ShopItem>()

    override fun addShopItem(item: ShopItem) {
        val itemWithIncrementId = item.copy(id = autoIncrementId++)
        shopList.add(itemWithIncrementId)
    }

    override fun deleteShopItem(item: ShopItem) {
        shopList.remove(item)
    }

    override fun editShopItem(item: ShopItem) {
        val oldElement = getShopItem(item.id)
        shopList.remove(oldElement)
        shopList.add(item)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}