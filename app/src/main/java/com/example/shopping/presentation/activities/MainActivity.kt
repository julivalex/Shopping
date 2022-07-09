package com.example.shopping.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.R
import com.example.shopping.presentation.adapters.ShopListAdapter
import com.example.shopping.presentation.fragments.ShopItemFragment
import com.example.shopping.presentation.viewmodels.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val shopListAdapter: ShopListAdapter = ShopListAdapter()

    private lateinit var shopFloatingActionButton: FloatingActionButton
    private lateinit var shopRecyclerView: RecyclerView
    private var shopItemContainerLand: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
        setupViews()
        setupRecyclerView()
        setupFloatingClickListener()
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener()
    }

    private fun setupViews() {
        shopFloatingActionButton = findViewById(R.id.shopFloatingActionButton)
        shopRecyclerView = findViewById(R.id.shopRecyclerView)
        shopItemContainerLand = findViewById(R.id.shopItemContainerLand)
    }

    private fun setupFloatingClickListener() {
        shopFloatingActionButton.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddItem(context = this)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun setupClickListener() {
        shopListAdapter.onShopItemClick = {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentEditItem(context = this, shopItemId = it.id)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun isOnePaneMode(): Boolean {
        return shopItemContainerLand == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.shopItemContainerLand, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClick = { item ->
            viewModel.changeEnabledState(item)
        }
    }

    private fun setupRecyclerView() {
        with(shopRecyclerView) {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
    }

    private fun setupSwipeListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(shopRecyclerView)
    }
}