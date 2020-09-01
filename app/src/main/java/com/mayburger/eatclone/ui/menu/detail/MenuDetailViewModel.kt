package com.mayburger.eatclone.ui.menu.detail

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class MenuDetailViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MenuDetailNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {
    }

    val menu = ObservableField(MenuDataModel())
    val price = ObservableField("")

    fun setPrice(){
        price.set("${menu.get()?.price} ${menu.get()?.currency}")
    }

    val quantity = ObservableField(0)

    val position = ObservableField(0)
    fun onClickAdd(){
        quantity.set(quantity.get()?.plus(1))
        navigator?.onChangeQuantity(quantity.get()?:0)
    }

    fun onClickRemove(){
        quantity.set(quantity.get()?.plus(1))
        navigator?.onChangeQuantity(quantity.get()?:0)
    }
}