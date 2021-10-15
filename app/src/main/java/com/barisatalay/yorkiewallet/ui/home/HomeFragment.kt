package com.barisatalay.yorkiewallet.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.barisatalay.domain.Constants
import com.barisatalay.yorkiewallet.R
import com.barisatalay.yorkiewallet.databinding.FragmentHomeBinding
import com.barisatalay.yorkiewallet.ui.base.BaseFragment
import com.barisatalay.yorkiewallet.ui.home.adapter.TokenListRecyclerAdapter
import com.barisatalay.yorkiewallet.util.extension.observeNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val getLayoutId = R.layout.fragment_home
    private val homeViewModel: HomeViewModel by viewModels()
    private val adapterWallet by lazy {
        TokenListRecyclerAdapter(onItemClick = {
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutWalletToken.rvWallet.adapter = adapterWallet
        //binding.layoutWalletToken.rvLiquidity.adapter = adapterWallet
        //binding.layoutWalletToken.rvYieldFarming.adapter = adapterWallet

        listenTokenList()
        homeViewModel.getTokenList(Constants.WALLET_EXAMPLE)
    }

    private fun listenTokenList() {
        homeViewModel.listenTokenList(Constants.WALLET_EXAMPLE)

        homeViewModel.walletTokenList.observeNotNull(viewLifecycleOwner) {
            adapterWallet.show(it)
        }
    }
}