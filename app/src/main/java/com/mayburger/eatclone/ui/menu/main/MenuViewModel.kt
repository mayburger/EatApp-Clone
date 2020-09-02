package com.mayburger.eatclone.ui.menu.main

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.model.events.MenuQuantityChangeEvent
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider
import kotlinx.coroutines.Dispatchers

class MenuViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MenuNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {
        when (obj) {
            is MenuQuantityChangeEvent -> {

            }
        }
    }

    val _forceUpdate = MutableLiveData<Boolean>(false)

    val restaurant = ObservableField(RestaurantDataModel())

    val orders = MutableLiveData<ArrayList<MenuDataModel>>()
    val totalOrder = MutableLiveData("")
    val totalPrice = MutableLiveData("")

    val menus = _forceUpdate.switchMap {
        liveData(Dispatchers.IO) {
            try {
                emit(dataManager.getMenus(restaurant.get()?.id))
            } catch (e: Exception) {
                navigator?.onError(e.message)
            }
        }
    }
}