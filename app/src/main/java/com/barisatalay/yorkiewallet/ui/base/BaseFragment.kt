package com.barisatalay.yorkiewallet.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.barisatalay.yorkiewallet.BR
import com.barisatalay.yorkiewallet.util.extension.observeNotNull
import com.barisatalay.yorkiewallet.view.LoadingDialog

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    abstract val getLayoutId: Int
    abstract fun getViewModelForBase(): BaseViewModel
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(requireActivity()) }
    private var _binding: DB? = null
    protected val binding: DB
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        binding.setVariable(BR.viewModel, getViewModelForBase())
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLoading()
    }

    private fun subscribeLoading() {
        getViewModelForBase().isDataLoading.observeNotNull(this) {
            when (it) {
                true -> if (isAdded) loadingDialog.showLoading()
                false -> loadingDialog.hideLoading()
            }
        }
    }
}