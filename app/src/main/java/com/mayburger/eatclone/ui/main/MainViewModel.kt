package com.mayburger.eatclone.ui.main

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.google.firebase.firestore.ktx.toObject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.ui.restaurant.ItemRestaurantViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class MainViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {

    }

    val greetings = ObservableField("Hello ${dataManager.user.fullName}!")
    val restaurants = ObservableArrayList<ItemRestaurantViewModel>()


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