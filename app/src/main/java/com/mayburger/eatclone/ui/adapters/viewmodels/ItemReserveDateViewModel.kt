package com.mayburger.eatclone.ui.adapters.viewmodels

import androidx.databinding.ObservableField
import com.mayburger.eatclone.R
import com.mayburger.eatclone.ui.base.BaseHorizontalViewModel
import java.text.SimpleDateFormat
import java.util.*

class ItemReserveDateViewModel (val it:Date):
    BaseHorizontalViewModel {

    val selected = ObservableField(false)
    var navigator: Callback? = null

    fun day():ObservableField<String>{
        val calendar = Calendar.getInstance()
        val today = SimpleDateFormat("EEEE, MMM d, yyyy").format(calendar.time)
        calendar.add(Calendar.DATE,1)
        val tomorrow = SimpleDateFormat("EEEE, MMM d, yyyy").format(calendar.time)

        return when(SimpleDateFormat("EEEE, MMM d, yyyy").format(it)){
            today->ObservableField("Today")
            tomorrow-> ObservableField("Tomorrow")
            else-> ObservableField(SimpleDateFormat("EEEE").format(it))
        }
    }

    fun date():ObservableField<String>{
        val format = SimpleDateFormat("EEE, MMM d")
        return ObservableField(format.format(it))
    }

    fun onClickDate(){
        navigator?.onClickDate()
    }

    interface Callback{
        fun onClickDate()
    }

    override fun layoutId(): Int {
        return R.layout.item_reserve_date
    }
}