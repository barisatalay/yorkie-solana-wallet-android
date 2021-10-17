package com.barisatalay.yorkiewallet.util.bindingadapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.imageLoader
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.barisatalay.domain.model.NetworkType
import com.barisatalay.yorkiewallet.R

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

@BindingAdapter(value = ["networkType"], requireAll = false)
fun ImageView.loadNetworkType(networkType: NetworkType?) {
    val imageLoader = context.imageLoader

    val builder = ImageRequest.Builder(this.context)
        .placeholder(R.drawable.ic_unknown_token)
        .error(R.drawable.ic_unknown_token)
        .transformations(RoundedCornersTransformation(100F))
        .target(this)

    when (networkType) {
        NetworkType.SOLANA -> builder.data(R.drawable.ic_solana)
        null -> builder.data(R.drawable.ic_solana)
    }

    val request = builder.build()

    imageLoader.enqueue(request)
}