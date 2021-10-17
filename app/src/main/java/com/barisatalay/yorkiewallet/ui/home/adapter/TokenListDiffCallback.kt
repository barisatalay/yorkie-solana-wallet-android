package com.barisatalay.yorkiewallet.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.barisatalay.domain.model.TokenModel

class TokenListDiffCallback constructor(
    private var oldList: List<TokenModel>,
    private var newList: List<TokenModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].code == newList[newItemPosition].code
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]
        return newItem.code == oldItem.code
                && newItem.amount == oldItem.amount
                && newItem.priceUSDT == oldItem.priceUSDT
    }
}