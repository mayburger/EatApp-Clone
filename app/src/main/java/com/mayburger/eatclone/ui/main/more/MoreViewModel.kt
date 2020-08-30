package com.mayburger.eatclone.ui.main.more

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class MoreViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MoreNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {

    }
}