package com.mayburger.eatclone.ui.main

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.events.SelectRegionEvent
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class MainViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    val selectedBottomNav = ObservableField(0)
    val selectedBottomNavTitle = ObservableField("Explore")
    val region = ObservableField(dataManager.region)

    fun onClickSearch() {
        navigator?.onClickSearch()
    }

    fun onClickRegion() {
        if (selectedBottomNav.get() == 0) {
            navigator?.onClickRegion()
        }
    }

    override fun onEvent(obj: Any) {
        when (obj) {
            is SelectRegionEvent -> {
                region.set(dataManager.region)
            }
        }
    }
}