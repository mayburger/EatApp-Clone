package com.mayburger.eatclone.ui.main.explore

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

class ExploreViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<ExploreNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {

    }

    val greetings = ObservableField("Hello ${dataManager.user.fullName}!")
    val restaurants = MutableLiveData<ArrayList<ItemRestaurantViewModel>>()
    val meals = MutableLiveData<ArrayList<ItemMealViewModel>>()


    fun onClickSearch() {
        navigator?.onClickSearch()
    }

    fun getMeals() {
        val data = ArrayList<ItemMealViewModel>()
        dataManager.getMeals(4).addOnCompleteListener {
            if (it.isSuccessful) {
                it.result?.documents?.let {
                    for (i in it) {
                        data.add(ItemMealViewModel(i.toObject<MealDataModel>() ?: MealDataModel()))
                    }
                }
                meals.value = data
            }
        }
    }

    fun getRestaurants() {
        val data = ArrayList<ItemRestaurantViewModel>()
        dataManager.getRestaurants(5).addOnCompleteListener {
            if (it.isSuccessful) {
                it.result?.documents?.let {
                    for (i in it) {
                        data.add(
                            ItemRestaurantViewModel(
                                i.toObject<RestaurantDataModel>() ?: RestaurantDataModel()
                            )
                        )
                    }
                }
                restaurants.value = data
            }
        }
    }
}