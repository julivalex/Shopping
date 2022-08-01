package com.example.shopping.data.mapper

import com.example.shopping.data.db.ShopItemDbModel
import com.example.shopping.domain.models.ShopItem

class ShopListMapper {

    fun map(item: ShopItemDbModel): ShopItem {
        return ShopItem(
            id = item.id,
            name = item.name,
            count = item.count,
            enabled = item.enabled
        )
    }

    fun unmap(item: ShopItem): ShopItemDbModel {
        return ShopItemDbModel(
            id = item.id,
            name = item.name,
            count = item.count,
            enabled = item.enabled
        )
    }

    fun mapList(list: List<ShopItemDbModel>): List<ShopItem> {
        return list.map { map(it) }
    }
}