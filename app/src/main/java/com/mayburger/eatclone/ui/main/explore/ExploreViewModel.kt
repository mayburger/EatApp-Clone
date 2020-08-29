package com.mayburger.eatclone.ui.main.explore

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider
import kotlinx.coroutines.Dispatchers.IO

class ExploreViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<ExploreNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {

    }

    val greetings = ObservableField("Hello ${dataManager.user.fullName}!")

    val restaurants = liveData(IO){ emit(dataManager.getRestaurants()) }
    val meals = liveData(IO){emit(dataManager.getMeals())}

    fun onClickSearch() {
        navigator?.onClickSearch()
    }
}