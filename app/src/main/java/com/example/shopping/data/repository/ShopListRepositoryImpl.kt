package com.example.shopping.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.shopping.data.db.AppDatabase
import com.example.shopping.data.mapper.ShopListMapper
import com.example.shopping.domain.models.ShopItem
import com.example.shopping.domain.repository.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override fun addShopItem(item: ShopItem) {
        shopListDao.addShopItem(mapper.unmap(item))
    }

    override fun deleteShopItem(item: ShopItem) {
        shopListDao.deleteShopItem(item.id)
    }

    override fun editShopItem(item: ShopItem) {
        shopListDao.addShopItem(mapper.unmap(item))
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.map(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListDao.getShopList()
    }
}