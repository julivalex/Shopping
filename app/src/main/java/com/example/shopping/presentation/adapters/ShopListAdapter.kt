package com.example.shopping.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.databinding.ItemShopDisabledBinding
import com.example.shopping.domain.models.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    private val shopList = mutableListOf<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val binding =
            ItemShopDisabledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.bind(shopItem)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    class ShopItemViewHolder(private val binding: ItemShopDisabledBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shopItem: ShopItem) {
            binding.tvName.text = shopItem.name
            binding.tvCount.text = shopItem.count.toString()
            binding.root.setOnLongClickListener {
                return@setOnLongClickListener true
            }
        }
    }
}