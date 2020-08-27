package com.mayburger.eatclone.ui.main

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.toObject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.MealDataModel
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMealViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.base.BaseViewModel
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
    val meals = ObservableArrayList<ItemMealViewModel>()


    fun onClickSearch(){
        navigator?.onClickSearch()
    }

    fun getMeals(){
        meals.clear()
        dataManager.getMeals(4).addOnCompleteListener {
            if (it.isSuccessful){
                it.result?.documents?.let{
                    for (i in it){
                        meals.add(ItemMealViewModel(i.toObject<MealDataModel>()?:MealDataModel()))
                    }
                }
            }
        }
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