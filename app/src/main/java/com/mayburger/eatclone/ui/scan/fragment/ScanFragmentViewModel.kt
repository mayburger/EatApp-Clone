package com.mayburger.eatclone.ui.scan.fragment

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider
import kotlinx.coroutines.launch

class ScanFragmentViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<ScanFragmentNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {

    }

    val data = ObservableField(RestaurantDataModel())
    val isShowing = ObservableField(false)
    val isLoaded = ObservableField(false)
    val showLoading = ObservableField(false)
    val isQrShowing = ObservableField(false)
    val errorTitle = ObservableField("")
    val errorMessage = ObservableField("")

    fun getRestaurant(id: String) {
        showLoading.set(true)
        println("LOADING!")
        viewModelScope.launch {
            dataManager.getRestaurant(id.replace("eatapp:restaurant-", ""))?.let {
                data.set(it)
                isLoaded.set(true)
            } ?: kotlin.run {
                invalid()
            }
            showLoading.set(false)
        }
    }

    fun onClickRestaurant() {
        if (isLoaded.get() == true) {
            navigator?.onClickRestaurant(data.get() ?: RestaurantDataModel())
        }
    }

    fun invalid() {
        isShowing.set(true)
        errorTitle.set("Invalid QR")
        errorMessage.set("Invalid QR code! Please try another one")
    }

    fun reset() {
        errorTitle.set("")
        errorMessage.set("")
        isShowing.set(false)
        showLoading.set(false)
        isLoaded.set(false)
    }

}