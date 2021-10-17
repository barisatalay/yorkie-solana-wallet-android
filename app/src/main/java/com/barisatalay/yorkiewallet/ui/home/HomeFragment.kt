package com.barisatalay.yorkiewallet.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.barisatalay.domain.Constants
import com.barisatalay.domain.model.WalletModel
import com.barisatalay.yorkiewallet.R
import com.barisatalay.yorkiewallet.databinding.FragmentHomeBinding
import com.barisatalay.yorkiewallet.ui.base.BaseFragment
import com.barisatalay.yorkiewallet.ui.base.BaseViewModel
import com.barisatalay.yorkiewallet.ui.home.adapter.TokenListRecyclerAdapter
import com.barisatalay.yorkiewallet.ui.main.MainActivity
import com.barisatalay.yorkiewallet.ui.main.MainViewModel
import com.barisatalay.yorkiewallet.util.extension.observeNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), SwipeRefreshLayout.OnRefreshListener {
    override val getLayoutId = R.layout.fragment_home
    private val viewModel: HomeViewModel by viewModels()
    override fun getViewModelForBase() = viewModel
    private val adapterTokenList by lazy {
        TokenListRecyclerAdapter(onItemClick = {
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        listen()
    }

    private fun listen() {
        viewModel.listenActiveWallet()

        viewModel.isRemoteLoading.observeNotNull(viewLifecycleOwner){
            when (it) {
                true -> if (isAdded && binding.srlContainer.isRefreshing.not()) binding.srlContainer.isRefreshing = true
                false -> binding.srlContainer.isRefreshing = false
            }
        }

        viewModel.activeWalletLD.observeNotNull(viewLifecycleOwner) {
            prepareApiRequests(it)
            viewModel.listenTokenList(it.address)
        }

        viewModel.walletTokenListLD.observeNotNull(viewLifecycleOwner) {
            adapterTokenList.show(it)
        }
    }

    private fun prepareApiRequests(walletModel: WalletModel) {
        viewModel.isRemoteLoading.postValue(true)
        viewModel.getTokenList(walletModel.address, walletModel.networkType)
    }

    private fun initUi() {
        binding.srlContainer.setOnRefreshListener(this)
        binding.layoutWalletToken.layoutWalletInfoWallet.rvWallet.adapter = adapterTokenList
        //TODO binding.layoutWalletToken.rvLiquidity.adapter = adapterWallet
        //TODO binding.layoutWalletToken.rvYieldFarming.adapter = adapterWallet

        binding.ivMenu.setOnClickListener {
            (activity as? MainActivity)?.toggleMenu()
        }

    }

    override fun onRefresh() {
        viewModel.activeWalletLD.value?.let {
            prepareApiRequests(it)
        }
    }
}