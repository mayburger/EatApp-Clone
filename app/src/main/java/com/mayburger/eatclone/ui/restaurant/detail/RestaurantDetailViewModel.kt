package com.mayburger.eatclone.ui.restaurant.detail

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider
import kotlinx.coroutines.Dispatchers

class RestaurantDetailViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<RestaurantDetailNavigator>(dataManager, schedulerProvider) {



    val restaurant = ObservableField(RestaurantDataModel())
    val restaurants = liveData(Dispatchers.IO){
        try{
        emit(dataManager.getRestaurants())
        }catch (e:Exception){
            navigator?.onError(e.message)
        }
    }
    val tags = ObservableField(dataManager)
    val mIsTheTitleVisible = ObservableField(false)

    val showNotes = ObservableBoolean(false)
    val showCall = ObservableBoolean(false)
    val showReadMore = ObservableBoolean(false)
    val notesMaxLine = ObservableField(Int.MAX_VALUE)

    fun buildVisibility(){
        showNotes.set(restaurant.get()?.notes.isNullOrEmpty().not())
        showCall.set(restaurant.get()?.phone.isNullOrEmpty().not())
    }

    fun onClickReserve(){
        navigator?.onClickReserve()
    }

    fun onClickCall(){
        navigator?.onClickCall()
    }

    fun onClickMaps(){
        navigator?.onClickMaps()
    }

    fun onClickTripAdvisor(){
        navigator?.onClickTripAdvisor()
    }

}