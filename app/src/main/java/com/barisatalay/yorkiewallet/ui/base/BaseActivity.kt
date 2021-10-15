package com.barisatalay.yorkiewallet.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.barisatalay.yorkiewallet.R

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: DB
    abstract val getLayoutId: Int
    private var backPressed: Long = 0
    protected var isCancelable = true
    protected var isCancelableTouchOutside = true
    protected abstract fun isBaseBackProcessEnabled(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()

    }

    private fun bind() {
        binding = DataBindingUtil.setContentView(this, getLayoutId)
        //binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
    }

    override fun onBackPressed() {
        if (isBaseBackProcessEnabled()) {
            handleBackBtnIfDuplicateEnabled()
        } else {
            super.onBackPressed()
        }
    }

    private fun handleBackBtnIfDuplicateEnabled() {
        if (backPressed + 1500 > System.currentTimeMillis()) {
            finishAndRemoveTask()
        } else {
            var toastMessage = getString(R.string.tap_back_btn_twice)

            Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
        }
        backPressed = System.currentTimeMillis()
    }

}