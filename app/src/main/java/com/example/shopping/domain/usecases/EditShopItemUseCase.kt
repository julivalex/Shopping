package com.example.shopping.domain.usecases

import com.example.shopping.domain.models.ShopItem
import com.example.shopping.domain.repository.ShopListRepository

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}