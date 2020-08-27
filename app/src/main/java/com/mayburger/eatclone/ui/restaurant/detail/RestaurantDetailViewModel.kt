package com.mayburger.eatclone.ui.restaurant.detail

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.google.firebase.firestore.ktx.toObject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class RestaurantDetailViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<RestaurantDetailNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {

    }

    val restaurant = ObservableField(RestaurantDataModel())
    val restaurants = ObservableArrayList<ItemRestaurantViewModel>()
    val tags = ObservableField(dataManager)
    val mIsTheTitleVisible = ObservableField(false)

    val showNotes = ObservableBoolean(false)
    val showReadMore = ObservableBoolean(false)
    val notesMaxLine = ObservableField(Int.MAX_VALUE)

    fun buildVisibility(){
        showNotes.set(restaurant.get()?.notes.isNullOrEmpty().not())
    }

    fun onClickReserve(){
        navigator?.onClickReserve()
    }

    fun onClickMaps(){
        navigator?.onClickMaps()
    }

    fun onClickTripAdvisor(){
        navigator?.onClickTripAdvisor()
    }

    fun getRestaurants() {
        restaurants.clear()
        dataManager.getRestaurants(5).addOnCompleteListener {
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