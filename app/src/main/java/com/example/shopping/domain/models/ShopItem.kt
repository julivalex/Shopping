package com.example.shopping.domain.models

const val UNDEFINED_ID = -1

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    val id: Int = UNDEFINED_ID
)



