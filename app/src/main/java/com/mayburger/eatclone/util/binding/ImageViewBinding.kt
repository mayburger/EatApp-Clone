package com.mayburger.eatclone.util.binding

import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mayburger.eatclone.R


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

    @BindingAdapter(value = ["imageUserUrl", "imageUserName", "imageUserEdit"], requireAll = false)
    @JvmStatic
    fun bindImageUser(view: ImageView, imageUserUrl: String? = "url", userName: String? = "", uri: Uri? = null) {

        var drawable = ContextCompat.getDrawable(view.context, R.drawable.ic_search)

        val name = userName ?: ""
        if (name.isNotEmpty()) {
            var initialName = name.substring(0, 1)
            val names = name.trim().split("\\s+".toRegex()).dropWhile { it.isEmpty() }.toTypedArray()
            if (names.size > 1) {
                initialName = names[0].substring(0, 1) + names[1].substring(0, 1)
            }

            val colorGenerator = ColorGenerator.MATERIAL
            val color = colorGenerator.getColor(userName)

            drawable = TextDrawable.builder().buildRound(initialName.toUpperCase(), color)
        }

        view.setImageDrawable(drawable)

    }
}