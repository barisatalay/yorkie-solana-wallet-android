package com.barisatalay.yorkiewallet.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.barisatalay.domain.model.WalletModel

class WalletDiffCallback constructor(
    private var oldList: List<WalletModel>,
    private var newList: List<WalletModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].address == newList[newItemPosition].address
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
        return newItem.address == oldItem.address
                && newItem.label == oldItem.label
    }
}