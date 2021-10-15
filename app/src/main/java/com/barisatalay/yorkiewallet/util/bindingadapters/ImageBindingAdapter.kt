package com.barisatalay.yorkiewallet.util.bindingadapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.imageLoader
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation

@BindingAdapter(value = ["imageUrl", "placeHolderImageId", "isCircularImage"], requireAll = false)
fun ImageView.load(url: String?, placeholder: Drawable?, isCircularImage: Boolean? = false) {
    val imageLoader = context.imageLoader

    val builder = ImageRequest.Builder(this.context)
        .placeholder(placeholder)
        .error(placeholder)
        .data(url)
        .target(this)

    if (isCircularImage == true) {
        builder.transformations(RoundedCornersTransformation(100F))
    }

    val request = builder.build()

    imageLoader.enqueue(request)
}