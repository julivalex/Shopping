package com.example.shopping.domain.usecases

import androidx.lifecycle.LiveData
import com.example.shopping.domain.models.ShopItem
import com.example.shopping.domain.repository.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}