package com.mayburger.eatclone.ui.adapters.viewmodels

import androidx.databinding.ObservableField
import com.mayburger.eatclone.R
import com.mayburger.eatclone.ui.base.BaseHorizontalViewModel
import java.text.SimpleDateFormat
import java.util.*

class ItemReserveAvailableTimesViewModel (val it:Date):
    BaseHorizontalViewModel {

    val selected = ObservableField(false)
    var navigator: Callback? = null

    fun time():ObservableField<String>{
        val format = SimpleDateFormat("HH:mm")
        return ObservableField(format.format(it))
    }

    fun onClickTime(){
        navigator?.onClickTime()
    }

    interface Callback{
        fun onClickTime()
    }

    override fun layoutId(): Int {
        return R.layout.item_reserve_available_times
    }
}