package com.example.shopping

import android.app.Application
import com.example.shopping.di.DaggerApplicationComponent

class ShoppingListApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}