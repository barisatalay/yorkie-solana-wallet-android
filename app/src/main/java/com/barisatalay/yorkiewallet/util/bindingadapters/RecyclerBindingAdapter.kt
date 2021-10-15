package com.barisatalay.yorkiewallet.util.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barisatalay.yorkiewallet.view.VerticalSpaceItemDecoration

@BindingAdapter(value = ["verticalItemSpace","alignCenter"],requireAll = false)
fun addVerticalSpace(recyclerView: RecyclerView, verticalSpace: Int, alignCenter: Boolean = false) {
    val verticalSpaceDivider = VerticalSpaceItemDecoration(verticalSpace, alignCenter)
    recyclerView.addItemDecoration(verticalSpaceDivider)
}