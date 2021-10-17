package com.barisatalay.yorkiewallet.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.barisatalay.domain.model.WalletModel
import com.barisatalay.yorkiewallet.R
import com.barisatalay.yorkiewallet.databinding.ItemWalletBinding
import com.barisatalay.yorkiewallet.util.extension.inflateBinding

class WalletRecyclerAdapter(
        private val onItemClick: (model: WalletModel) -> Unit
) : RecyclerView.Adapter<WalletViewHolder>() {
    private var data = mutableListOf<WalletModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val binding = parent.inflateBinding<ItemWalletBinding>(R.layout.item_wallet)
        return WalletViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        holder.bind(data[position], onItemClick)
    }

    override fun getItemCount() = data.size

    fun show(newList: List<WalletModel>) {
        val diffCallback = WalletDiffCallback(data, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data = newList.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }
}