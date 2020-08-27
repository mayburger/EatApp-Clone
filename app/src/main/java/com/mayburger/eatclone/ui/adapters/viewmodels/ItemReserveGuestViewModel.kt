package com.mayburger.eatclone.ui.adapters.viewmodels

import androidx.databinding.ObservableField
import com.mayburger.eatclone.R
import com.mayburger.eatclone.ui.base.BaseHorizontalViewModel

class ItemReserveGuestViewModel(val it: Int) :
    BaseHorizontalViewModel {

    val selected = ObservableField(false)
    var navigator: Callback? = null

    val numberOfGuests = ObservableField(it.toString())

    fun onClickGuest() {
        navigator?.onClickGuest()
    }

    interface Callback {
        fun onClickGuest()
    }

    override fun layoutId(): Int {
        return R.layout.item_reserve_guest
    }
}