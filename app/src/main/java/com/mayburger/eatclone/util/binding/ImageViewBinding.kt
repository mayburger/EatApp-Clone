package com.mayburger.eatclone.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object ImageViewBinding {

    @BindingAdapter(value = ["imageResource", "imageUrl","withRoundedCorners"],requireAll = false)
    @JvmStatic
    fun setImageResource(view: ImageView, src: Int?, url: String?,withRoundedCorners:Boolean = false) {
        if (src != null){
            view.setBackgroundResource(src)
        }
        if(url.isNullOrBlank().not()){
            val glide = Glide.with(view.context).load(url)
            if(withRoundedCorners){
                glide.apply(RequestOptions().transform(RoundedCorners(16)))
            }
            glide.into(view)
        }
    }
}