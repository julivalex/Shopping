package com.example.shopping.presentation.extensions

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText


fun TextInputEditText.textChanged(check: () -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            check()
        }

        override fun afterTextChanged(p0: Editable?) {}
    })
}

