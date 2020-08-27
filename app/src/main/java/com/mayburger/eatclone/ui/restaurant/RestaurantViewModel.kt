package com.mayburger.eatclone.ui.restaurant

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class RestaurantViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<RestaurantNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {

    }
}