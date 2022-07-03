package com.example.shopping.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopping.domain.models.ShopItem
import com.example.shopping.domain.repository.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private var autoIncrementId = 0
    private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })
    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0 until 100) {
            val shopItem = ShopItem(name = "Name $i", count = i, enabled = true)
            addShopItem(shopItem)
        }
    }

    override fun addShopItem(item: ShopItem) {
        val itemWithIncrementId = item.copy(id = autoIncrementId++)
        shopList.add(itemWithIncrementId)
        updateList()
    }

    override fun deleteShopItem(item: ShopItem) {
        shopList.remove(item)
        updateList()
    }

    override fun editShopItem(item: ShopItem) {
        val oldElement = getShopItem(item.id)
        shopList.remove(oldElement)
        shopList.add(item)
        updateList()
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    private fun updateList() {
        shopListLiveData.value = shopList.toList()
    }
}