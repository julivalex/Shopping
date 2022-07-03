package com.example.shopping.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shopping.domain.models.ShopItem

class ShopListDiffCallback(
    private val oldShopList: List<ShopItem>,
    private val newShopList: List<ShopItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldShopList.size
    }

    override fun getNewListSize(): Int {
        return newShopList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldShopItem = oldShopList[oldItemPosition]
        val newShopItem = newShopList[newItemPosition]
        return oldShopItem.id == newShopItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldShopItem = oldShopList[oldItemPosition]
        val newShopItem = newShopList[newItemPosition]
        return oldShopItem == newShopItem
    }
}