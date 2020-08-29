package com.mayburger.eatclone.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


object ImageViewBinding {

    @BindingAdapter(value = ["imageResource", "imageUrl"], requireAll = false)
    @JvmStatic
    fun setImageResource(
        view: ImageView,
        src: Int?,
        url: String?
    ) {
        if (src != null) {
            view.setBackgroundResource(src)
        }
        if (url.isNullOrBlank().not()) {
            Glide.with(view.context).load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }
}