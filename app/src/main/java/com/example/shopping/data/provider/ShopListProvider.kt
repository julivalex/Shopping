package com.example.shopping.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.shopping.ShoppingListApp
import com.example.shopping.data.db.ShopListDao
import javax.inject.Inject

class ShopListProvider : ContentProvider() {

    private val component by lazy {
        (context as ShoppingListApp).component
    }

    @Inject
    lateinit var shopListDao: ShopListDao

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("com.example.shopping", "shop_items", GET_SHOP_ITEMS_QUERY)
        addURI("com.example.shopping", "shop_items/#", GET_SHOP_ITEM_BY_ID_QUERY)
    }

    override fun onCreate(): Boolean {
        component.inject(this)
        return true
    }

    override fun query(
        uri: Uri,
        protection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = when (uriMatcher.match(uri)) {
        GET_SHOP_ITEMS_QUERY -> shopListDao.getShopListCursor()
        else -> null
    }


    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    companion object {
        private const val GET_SHOP_ITEMS_QUERY = 100
        private const val GET_SHOP_ITEM_BY_ID_QUERY = 101
    }
}