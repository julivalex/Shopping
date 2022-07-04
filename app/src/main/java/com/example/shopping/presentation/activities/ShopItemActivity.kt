package com.example.shopping.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shopping.databinding.ActivityShopItemBinding
import com.example.shopping.presentation.viewmodels.ShopItemViewModel

class ShopItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopItemBinding

    private lateinit var viewModel: ShopItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ShopItemViewModel::class.java)

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        Log.d("rLog", mode.toString())
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extraMode"
        private const val EXTRA_SHOP_ITEM_ID = "shopItemId"
        private const val MODE_EDIT = "modeEdit"
        private const val MODE_ADD = "modeAdd"

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            return intent
        }
    }
}