package com.mayburger.eatclone.ui.scan

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class ScanViewModel @ViewModelInject constructor(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
        BaseViewModel<ScanNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {

    }
}