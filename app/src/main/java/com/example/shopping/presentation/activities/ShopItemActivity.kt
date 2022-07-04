package com.example.shopping.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shopping.databinding.ActivityShopItemBinding

class ShopItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
    }
}