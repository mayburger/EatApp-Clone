package com.mayburger.eatclone.ui.scan

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider
import kotlinx.coroutines.launch

class ScanViewModel @ViewModelInject constructor(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
        BaseViewModel<ScanNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {

    }

    val data = ObservableField(RestaurantDataModel())
    val isShowing = ObservableField(false)
    val isLoaded = ObservableField(false)
    val isQrShowing = ObservableField(false)

    fun getRestaurant(id:String){
        viewModelScope.launch {
            println("It is called!")
            data.set(dataManager.getRestaurant(id.replace("eatapp:restaurant-","")))
            isLoaded.set(true)
        }
    }

}