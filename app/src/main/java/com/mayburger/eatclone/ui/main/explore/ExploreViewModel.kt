package com.mayburger.eatclone.ui.main.explore

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider
import kotlinx.coroutines.Dispatchers.IO

class ExploreViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<ExploreNavigator>(dataManager, schedulerProvider) {

    val greetings = ObservableField("Hello ${dataManager.user.fullName}!")

    val forceUpdate = MutableLiveData<Boolean>(false)

    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        forceUpdate.value = true
    }

    val isRefreshing = MutableLiveData<Boolean>(false)

    val restaurants = forceUpdate.switchMap {
        liveData(IO) {
            try {
                emit(dataManager.getRestaurants())
            } catch (e: Exception) {
                navigator?.onError(e.message)
            }
            isRefreshing.postValue(false)
        }
    }

    var meals = forceUpdate.switchMap {
        liveData(IO) {
            try {
                emit(dataManager.getMeals())
            } catch (e: Exception) {
                navigator?.onError(e.message)
            }
        }
    }

    override fun onEvent(obj: Any) {

    }
}