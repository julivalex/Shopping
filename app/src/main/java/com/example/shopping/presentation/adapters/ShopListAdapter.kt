package com.example.shopping.presentation.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.R
import com.example.shopping.databinding.ItemShopDisabledBinding
import com.example.shopping.databinding.ItemShopEnabledBinding
import com.example.shopping.domain.models.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    lateinit var onShopItemLongClick: (ShopItem) -> Unit
    lateinit var onShopItemClick: (ShopItem) -> Unit

    var count = 0
    var count2 = 0

    var shopList = listOf<ShopItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("rLog", "onCreateViewHolder = ${++count2}")
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("view type is unknown")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        Log.d("rLog", "onBindViewHolder = ${++count}")
        val shopItem = shopList[position]

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

    override fun getItemCount(): Int {
        return shopList.size
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView = view.findViewById(R.id.tv_count)
    }

    override fun getItemViewType(position: Int) = if (shopList[position].enabled) {
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