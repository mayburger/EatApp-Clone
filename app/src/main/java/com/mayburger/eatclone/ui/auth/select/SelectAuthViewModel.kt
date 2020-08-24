package com.mayburger.eatclone.ui.auth.select

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class SelectAuthViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<SelectAuthNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {

    }

    fun onClickLogin() {
        navigator?.onClickLogin()
    }

    fun onClickRegister() {
        navigator?.onClickRegister()
    }
}