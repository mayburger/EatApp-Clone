package com.mayburger.eatclone.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class SplashViewModel @ViewModelInject constructor(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
        BaseViewModel<SplashNavigator>(dataManager, schedulerProvider) {


}