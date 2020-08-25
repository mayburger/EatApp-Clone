package com.mayburger.eatclone.ui

import androidx.databinding.ObservableField

class ItemTagViewModel{
    val selected = ObservableField(false)
    var navigator:Callback? = null

    fun onClickTag(){
        navigator?.onClickTag()
    }

    interface Callback{
        fun onClickTag()
    }
}