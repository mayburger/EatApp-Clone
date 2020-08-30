package com.mayburger.eatclone.ui.restaurant.reserve

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemReserveAvailableTimesViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemReserveDateViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemReserveGuestViewModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider
import java.util.*
import kotlin.collections.ArrayList

class ReserveViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<ReserveNavigator>(dataManager, schedulerProvider) {



    val restaurant = ObservableField(RestaurantDataModel())

    val selectedDate = ObservableField(Calendar.getInstance().time)
    val selectedNumberOfGuests = ObservableField(0)
    val selectedTime = ObservableField(Calendar.getInstance().time)

    fun onClickClose() {
        navigator?.onClickClose()
    }

    fun getItemReserveDateViewModel(selected: Int): ArrayList<ItemReserveDateViewModel> {
        val viewModels = ArrayList<ItemReserveDateViewModel>()
        for (i in dataManager.get30Days()) {
            viewModels.add(
                ItemReserveDateViewModel(
                    i
                )
            )
        }
        viewModels[selected].selected.set(true)
        return viewModels
    }

    fun getItemReserveGuestViewModel(selected: Int): ArrayList<ItemReserveGuestViewModel> {
        val viewModels = ArrayList<ItemReserveGuestViewModel>()
        for (i in 1..50) {
            viewModels.add(
                ItemReserveGuestViewModel(
                    i
                )
            )
        }
        viewModels[selected].selected.set(true)
        return viewModels
    }

    fun getItemReserveAvailableTimesViewModel(selected: Int): ArrayList<ItemReserveAvailableTimesViewModel> {
        val viewModels = ArrayList<ItemReserveAvailableTimesViewModel>()
        for (i in dataManager.getAvailableTimes()) {
            viewModels.add(
                ItemReserveAvailableTimesViewModel(
                    i
                )
            )
        }
        viewModels[selected].selected.set(true)
        return viewModels
    }

    override fun onEvent(obj: Any) {

    }

}