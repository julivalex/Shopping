package com.example.shopping.domain.usecases

import com.example.shopping.domain.models.ShopItem
import com.example.shopping.domain.repository.ShopListRepository

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun deleteShopItem(item: ShopItem) {
        shopListRepository.deleteShopItem(item)
    }
}