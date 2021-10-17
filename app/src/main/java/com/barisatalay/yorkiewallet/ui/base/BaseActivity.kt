package com.barisatalay.yorkiewallet.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.barisatalay.yorkiewallet.R
import com.barisatalay.yorkiewallet.BR
import com.barisatalay.yorkiewallet.util.extension.observeNotNull
import com.barisatalay.yorkiewallet.view.LoadingDialog

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: DB
    abstract val getLayoutId: Int
    abstract fun getViewModelForBase(): BaseViewModel
    private var backPressed: Long = 0
    protected var isCancelable = true
    protected var isCancelableTouchOutside = true
    protected abstract fun isBaseBackProcessEnabled(): Boolean
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        subscribeLoading()
    }

    private fun bind() {
        binding = DataBindingUtil.setContentView(this, getLayoutId)
        binding.setVariable(BR.viewModel, getViewModelForBase())
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

    private fun subscribeLoading() {
        getViewModelForBase().isDataLoading.observeNotNull(this) {
            when (it) {
                true -> if (this.isFinishing.not()) loadingDialog.showLoading()
                false -> loadingDialog.hideLoading()
            }
        }
    }
}