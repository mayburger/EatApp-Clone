package com.mayburger.eatclone.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class SearchViewModel @ViewModelInject constructor(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
        BaseViewModel<SearchNavigator>(dataManager, schedulerProvider) {


}