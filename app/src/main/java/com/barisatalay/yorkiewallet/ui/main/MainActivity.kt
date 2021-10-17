package com.barisatalay.yorkiewallet.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.barisatalay.yorkiewallet.R
import com.barisatalay.yorkiewallet.databinding.ActivityMainBinding
import com.barisatalay.yorkiewallet.ui.base.BaseActivity
import com.barisatalay.yorkiewallet.ui.main.adapter.WalletRecyclerAdapter
import com.barisatalay.yorkiewallet.util.extension.observeNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val getLayoutId = R.layout.activity_main
    val viewModel: MainViewModel by viewModels()
    override fun getViewModelForBase() = viewModel
    private val navController by lazy { findNavController(R.id.nav_host_fragment_content_main) }

    override fun isBaseBackProcessEnabled() = true
    private val adapterWallet by lazy {
        WalletRecyclerAdapter(onItemClick = {
            viewModel.updateActiveWallet(it.address)
            menuClose()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        listen()

    }

    override fun onBackPressed() {
        if (navController.navigateUp().not())
            super.onBackPressed()
    }

    private fun initUi() {
        binding.navViewLayout.rvWallet.adapter = adapterWallet

        binding.navViewLayout.tvConnectWallet.setOnClickListener {
            openAddWallet()
        }
    }

    private fun listen() {
        viewModel.getWalletList()

        viewModel.walletInfoLD.observeNotNull(this) {
            adapterWallet.show(it)

            showIfWalletEmpty(it.isEmpty())
        }
    }

    private fun showIfWalletEmpty(isWalletEmpty: Boolean) {
        if (isWalletEmpty && binding.drawerLayout.isOpen.not())
            binding.drawerLayout.open()
    }

    fun toggleMenu() {
        if (binding.drawerLayout.isOpen)
            menuClose()
        else
            menuOpen()
    }

    private fun menuOpen() {
        binding.drawerLayout.open()
    }

    private fun menuClose() {
        binding.drawerLayout.close()
    }

    private fun openAddWallet() {
        navController.navigate(R.id.action_nav_home_to_nav_connect_wallet)
        menuClose()
    }

}