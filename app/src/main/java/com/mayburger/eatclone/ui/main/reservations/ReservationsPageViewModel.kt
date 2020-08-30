package com.mayburger.eatclone.ui.main.reservations

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class ReservationsPageViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<ReservationsPageNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {

    }
}