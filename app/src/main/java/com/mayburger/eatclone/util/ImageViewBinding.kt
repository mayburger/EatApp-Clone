package com.mayburger.eatclone.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageViewBinding {

    @BindingAdapter("imageResource")
    @JvmStatic
    fun setImageResource(view: ImageView, resource:Int){
        view.setImageResource(resource)
    }
    @BindingAdapter("backgroundResource")
    @JvmStatic
    fun setBackgroundResource(view: View, resource:Int){
        view.setBackgroundResource(resource)
    }

}