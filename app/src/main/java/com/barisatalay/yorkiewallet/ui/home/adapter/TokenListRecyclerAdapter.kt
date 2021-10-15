package com.barisatalay.yorkiewallet.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.barisatalay.yorkiewallet.R
import com.barisatalay.yorkiewallet.databinding.ItemTokenBinding
import com.barisatalay.domain.model.TokenModel
import com.barisatalay.yorkiewallet.util.extension.inflateBinding

class TokenListRecyclerAdapter(
    private val onItemClick: (model: TokenModel) -> Unit
) : RecyclerView.Adapter<TokenListViewHolder>() {
    private var data = mutableListOf<TokenModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokenListViewHolder {
        val binding = parent.inflateBinding<ItemTokenBinding>(R.layout.item_token)
        return TokenListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TokenListViewHolder, position: Int) {
        holder.bind(data[position], onItemClick)
    }

    override fun getItemCount() = data.size

    fun show(newList: List<TokenModel>) {
        val diffCallback = TokenListDiffCallback(data, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data = newList.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }
}