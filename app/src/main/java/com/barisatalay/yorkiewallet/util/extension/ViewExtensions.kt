package com.barisatalay.yorkiewallet.util.extension

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> ViewGroup.inflateBinding(@LayoutRes layoutId: Int, attachToParent: Boolean = false): T {
    return DataBindingUtil.inflate(context.inflater(), layoutId, this, attachToParent)
}