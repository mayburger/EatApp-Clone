package com.mayburger.eatclone.ui.region

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class SelectRegionViewModel @ViewModelInject constructor(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
        BaseViewModel<SelectRegionNavigator>(dataManager, schedulerProvider) {

        override fun onEvent(obj: Any) {

        }
}