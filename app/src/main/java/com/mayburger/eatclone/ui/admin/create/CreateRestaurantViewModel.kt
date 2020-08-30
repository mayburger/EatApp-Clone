package com.mayburger.eatclone.ui.admin.create

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mayburger.eatclone.R
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.model.TagDataModel
import com.mayburger.eatclone.model.events.RestaurantUpdateEvent
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.ext.toStringJson
import com.mayburger.eatclone.util.rx.LiveBus
import com.mayburger.eatclone.util.rx.SchedulerProvider

class CreateRestaurantViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<CreateRestaurantNavigator>(dataManager, schedulerProvider) {

    val id = ObservableField("")
    val name = ObservableField("")
    val address = ObservableField("")
    val image = ObservableField("")
    val notes = ObservableField("")
    val cuisine = ObservableField("")
    val distance = ObservableField("")
    val phone = ObservableField("")
    val selectedPrice = ObservableField(0)
    val selectedTags = ObservableArrayList<TagDataModel>()
    val tags = ObservableArrayList<TagDataModel>()
    val isEditMode = ObservableField(false)

    fun getTags(context: Context) {
        val tags =
            Gson().fromJson<ArrayList<TagDataModel>>((R.raw.tags).toStringJson(context), object :
                TypeToken<ArrayList<TagDataModel>>() {}.type)
        this.tags.addAll(tags)
    }

    fun onClickCreate() {
        navigator?.showLoading()
        val restaurant = RestaurantDataModel(
            id = id.get() ?: "",
            name = name.get() ?: "",
            address = address.get() ?: "",
            cuisine = cuisine.get() ?: "",
            distance = distance.get() ?: "",
            phone = phone.get()?:"",
            image = image.get() ?: "",
            notes = notes.get() ?: "",
            _price = selectedPrice.get() ?: 0,
            tags = selectedTags
        )
        if (isEditMode.get() == true) {
            update(restaurant)
        } else {
            create(restaurant)
        }
    }

    private fun create(restaurantDataModel: RestaurantDataModel) {
        dataManager.createRestaurant(
            restaurantDataModel
        ).addOnCompleteListener { create ->
            if (create.isSuccessful) {
                create.result?.get()?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        navigator?.hideLoading()
                        navigator?.finishActivity()
                        create.result?.update("id", it.result?.id)
                    }
                }
            }
        }
    }

    private fun update(restaurantDataModel: RestaurantDataModel) {
        dataManager.updateRestaurant(
            restaurantDataModel
        ).addOnCompleteListener { create ->
            if (create.isSuccessful) {
                LiveBus.post(RestaurantUpdateEvent())
                navigator?.hideLoading()
                navigator?.finishActivity()
            }
        }
    }

    fun priceOne() {
        selectedPrice.set(0)
    }

    fun priceTwo() {
        selectedPrice.set(1)
    }

    fun priceThree() {
        selectedPrice.set(2)
    }

}