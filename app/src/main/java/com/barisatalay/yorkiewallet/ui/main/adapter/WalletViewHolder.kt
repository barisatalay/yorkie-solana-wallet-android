package com.barisatalay.yorkiewallet.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.barisatalay.domain.model.WalletModel
import com.barisatalay.yorkiewallet.databinding.ItemWalletBinding

class WalletViewHolder constructor(
    private val binding: ItemWalletBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: WalletModel, onItemClick: (model: WalletModel) -> Unit) {
        binding.item = item

        binding.clContainer.setOnClickListener { onItemClick.invoke(item) }
    }
}