package com.mayburger.eatclone.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mayburger.eatclone.util.rx.LiveBus


abstract class BaseEventActivity:AppCompatActivity(){


    abstract fun onEvent(obj:Any)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LiveBus.getDefault().observe(this, Observer {
            onEvent(it.getValue())
        })
    }
}

