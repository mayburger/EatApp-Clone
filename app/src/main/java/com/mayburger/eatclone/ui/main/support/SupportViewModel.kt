package com.mayburger.eatclone.ui.main.support

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class SupportViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<SupportNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {

    }


}