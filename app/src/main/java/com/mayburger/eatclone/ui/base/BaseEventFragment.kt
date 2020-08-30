package com.mayburger.eatclone.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mayburger.eatclone.util.rx.LiveBus


abstract class BaseEventFragment: Fragment(){
    abstract fun onEvent(obj:Any)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LiveBus.getDefault().observe(viewLifecycleOwner, Observer {
            onEvent(it.getValue())
        })
    }
}