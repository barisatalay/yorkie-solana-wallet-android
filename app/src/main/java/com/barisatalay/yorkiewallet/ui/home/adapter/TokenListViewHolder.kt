package com.barisatalay.yorkiewallet.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.barisatalay.yorkiewallet.databinding.ItemTokenBinding
import com.barisatalay.domain.model.TokenModel

class TokenListViewHolder constructor(
    private val binding: ItemTokenBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: TokenModel, onItemClick: (model: TokenModel) -> Unit) {
        binding.item = item

        binding.clContainer.setOnClickListener { onItemClick.invoke(item) }
    }
}