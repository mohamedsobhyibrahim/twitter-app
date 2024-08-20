package com.example.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding>(
    val bindingInflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!


    abstract fun bindViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
        bindViews()
    }

    protected fun handleError(throwable: Throwable) {
        handleError(message = throwable.message)
    }

    protected fun handleError(message: String?) {
        Toast.makeText(this, message ?: "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    protected fun showMessage(@StringRes stringId: Int) {
        showMessage(message = getString(stringId))
    }

    protected fun showMessage(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}