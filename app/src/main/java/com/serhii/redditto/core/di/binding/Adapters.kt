package com.serhii.redditto.core.di.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("fromUrl")
fun loadImageFromUrl(view: ImageView, url: String?) {
    Glide.with(view)
            .load(url)
            .into(view)
}