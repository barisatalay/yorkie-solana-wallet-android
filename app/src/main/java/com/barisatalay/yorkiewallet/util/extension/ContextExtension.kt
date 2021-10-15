package com.barisatalay.yorkiewallet.util.extension

import android.content.Context
import android.view.LayoutInflater

fun Context.inflater(): LayoutInflater {
    return LayoutInflater.from(this)
}