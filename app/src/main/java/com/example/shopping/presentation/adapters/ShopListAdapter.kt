package com.example.shopping.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.R
import com.example.shopping.domain.models.ShopItem
import com.example.shopping.presentation.ShopItemDiffCallback
import com.example.shopping.presentation.viewholders.ShopItemViewHolder
import java.lang.RuntimeException

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    lateinit var onShopItemLongClick: (ShopItem) -> Unit
    lateinit var onShopItemClick: (ShopItem) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("View type is unknown $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.view.setOnClickListener {
            onShopItemClick(shopItem)
        }
        holder.view.setOnLongClickListener {
            onShopItemLongClick(shopItem)
            return@setOnLongClickListener true
        }
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
    }

    override fun getItemViewType(position: Int) = if (getItem(position).enabled) {
        VIEW_TYPE_ENABLED
    } else {
        VIEW_TYPE_DISABLED
    }

    companion object {
        const val MAX_POOL_SIZE = 5
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
    }
}