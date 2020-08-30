package com.mayburger.eatclone.ui.onboarding

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.events.LoginEvent
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class OnBoardingViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<OnBoardingNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {
        when(obj){
            is LoginEvent->{
                navigator?.finishActivity()
            }
        }
    }

    val imageResource:ObservableField<Int> = ObservableField(0)
    val title = ObservableField(dataManager.boardingData[0].title)
    val subtitle = ObservableField(dataManager.boardingData[0].subtitle)
    val selectedPosition = ObservableField(0)

    fun onGetStarted() {
        navigator?.onGetStarted()
    }
}