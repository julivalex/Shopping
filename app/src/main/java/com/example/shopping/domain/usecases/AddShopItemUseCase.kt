package com.example.shopping.domain.usecases

import com.example.shopping.domain.models.ShopItem
import com.example.shopping.domain.repository.ShopListRepository

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItem(item: ShopItem) {
        shopListRepository.addShopItem(item)
    }
}