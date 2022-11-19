package com.example.shopping.domain.usecases

import com.example.shopping.domain.models.ShopItem
import com.example.shopping.domain.repository.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}