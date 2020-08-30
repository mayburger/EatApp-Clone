package com.mayburger.eatclone.util.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextViewBinding {

    @BindingAdapter("maxLines")
    @JvmStatic
    fun setMaxlines(view: TextView, maxlines: Int) {
        view.post {
            if (view.text != null) {
                view.maxLines = maxlines
            }
            Int.MAX_VALUE
        }
    }

    @BindingAdapter("nameAbbreviation")
    @JvmStatic
    fun setNameAbbreviation(view: TextView, _name: String) {
        if (_name.split(" ").size > 1) {
            view.text = "${_name.split(" ")[0][0]}${_name.split(" ")[1][0]}"
        } else {
            view.text = "${_name.split(" ")[0][0]}"
        }
    }

}