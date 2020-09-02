package com.mayburger.eatclone.ui.restaurant.browse

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider
import kotlinx.coroutines.Dispatchers

class RestaurantBrowseViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<RestaurantBrowseNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {

    }



    val _forceUpdate = MutableLiveData<Boolean>(false)

    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        _forceUpdate.value = true
    }

    val isRefreshing = MutableLiveData<Boolean>(false)

    val restaurants = _forceUpdate.switchMap {
        liveData(Dispatchers.IO) {
            try {
                emit(dataManager.getRestaurants())

            } catch (e: Exception) {
                navigator?.onError(e.message)
            }
            isRefreshing.postValue(false)
        }
    }

}