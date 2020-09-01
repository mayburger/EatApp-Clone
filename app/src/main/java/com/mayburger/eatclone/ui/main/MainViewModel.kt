package com.mayburger.eatclone.ui.main

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.events.SelectRegionEvent
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class MainViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    val selectedBottomNav = MutableLiveData(0)
    val selectedBottomNavTitle = ObservableField("Explore")
    val region = ObservableField(dataManager.region)

    fun onClickSearch() {
        navigator?.onClickSearch()
    }

    fun onClickScan(){
        navigator?.onClickScan()
    }

    fun onClickRegion() {
        if (selectedBottomNav.value == 0) {
            navigator?.onClickRegion()
        }
    }

    fun onClickBottomNav(position: Int) {
        selectedBottomNav.value = position
    }

    override fun onEvent(obj: Any) {
        when (obj) {
            is SelectRegionEvent -> {
                region.set(dataManager.region)
            }
        }
    }
}