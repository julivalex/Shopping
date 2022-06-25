package com.example.shopping.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopping.R
import com.example.shopping.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}