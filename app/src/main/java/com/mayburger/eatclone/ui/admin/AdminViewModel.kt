package com.mayburger.eatclone.ui.admin

import androidx.databinding.ObservableArrayList
import androidx.hilt.lifecycle.ViewModelInject
import com.google.firebase.firestore.ktx.toObject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.data.events.RestaurantUpdateEvent
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class AdminViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<AdminNavigator>(dataManager, schedulerProvider) {

    val restaurants = ObservableArrayList<ItemRestaurantViewModel>()

    override fun onEvent(obj: Any) {
        when(obj){
            is RestaurantUpdateEvent->{
                getRestaurants()
            }
        }
    }

    fun getRestaurants() {
        restaurants.clear()
        dataManager.getRestaurants().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result?.documents?.let {
                    for (i in it) {
                        restaurants.add(
                            ItemRestaurantViewModel(
                                i.toObject<RestaurantDataModel>() ?: RestaurantDataModel()
                            )
                        )
                    }
                }
            }
        }
    }
}