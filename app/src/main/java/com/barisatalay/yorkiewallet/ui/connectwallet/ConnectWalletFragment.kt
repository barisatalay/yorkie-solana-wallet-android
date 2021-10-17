package com.barisatalay.yorkiewallet.ui.connectwallet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.barisatalay.yorkiewallet.R
import com.barisatalay.yorkiewallet.databinding.FragmentConnectWalletBinding
import com.barisatalay.yorkiewallet.ui.base.BaseFragment
import com.barisatalay.yorkiewallet.util.extension.observeNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectWalletFragment : BaseFragment<FragmentConnectWalletBinding>() {
    override val getLayoutId = R.layout.fragment_connect_wallet
    private val viewModel: ConnectWalletViewModel by viewModels()
    override fun getViewModelForBase() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        listen()
    }

    private fun listen() {
        viewModel.closeLD.observeNotNull(viewLifecycleOwner){
            activity?.onBackPressed()
        }
    }

    private fun initUi() {
        binding.tvCancel.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.tvConnectWallet.setOnClickListener {
            val label = binding.etWalletLabel.text.toString()
            val publicAddress = binding.etWalletPublicAddress.text.toString()
            val network = binding.etWalletNetwork.text.toString()
            viewModel.addWallet(label, publicAddress, network)
        }
    }

}