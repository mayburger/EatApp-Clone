package com.mayburger.eatclone.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


abstract class BaseEventFragment: Fragment(){
    abstract fun onEvent(obj:Any)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}