package com.mayburger.eatclone.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseEventActivity:AppCompatActivity(){


    abstract fun onEvent(obj:Any)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

