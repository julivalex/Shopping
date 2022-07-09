package com.example.shopping.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shopping.R
import com.example.shopping.databinding.ActivityShopItemBinding
import com.example.shopping.domain.models.ShopItem
import com.example.shopping.presentation.fragments.ShopItemFragment

class ShopItemActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishListener {

    private lateinit var binding: ActivityShopItemBinding

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.shopItemContainer, fragment)
            .commit()
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extraMode"
        private const val EXTRA_SHOP_ITEM_ID = "shopItemId"
        private const val MODE_EDIT = "modeEdit"
        private const val MODE_ADD = "modeAdd"
        private const val MODE_UNKNOWN = "modeUnknown"

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

    override fun onEditingFinish() {
        finish()
    }
}