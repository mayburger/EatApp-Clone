package com.mayburger.eatclone.ui.order

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.model.events.MenuQuantityChangeEvent
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemCheckoutViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class CheckoutViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<CheckoutNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {
        when (obj) {
            is MenuQuantityChangeEvent -> {
                menus.change(obj.menu)
            }
        }
    }

    val menus = MutableLiveData<ArrayList<ItemCheckoutViewModel>>()
    val restaurant: ObservableField<ItemRestaurantViewModel> = ObservableField()

    val subtotal = MutableLiveData("0 AED")
    val tax = MutableLiveData("0 AED")
    val total = MutableLiveData("0 AED")

    fun addMenus(data: ArrayList<MenuDataModel>?) {
        menus.postValue(ArrayList<ItemCheckoutViewModel>().apply {
            data?.map {
                add(
                    ItemCheckoutViewModel(it)
                )
            }
        })
    }

    fun MutableLiveData<ArrayList<ItemCheckoutViewModel>>.change(menu: MenuDataModel) {
        val value = this.value ?: arrayListOf()
        value.indexWithId(menu.id)?.let {
            if (menu.quantity == 0) {
                value.removeAt(it)
            } else {
                value.set(it, ItemCheckoutViewModel(menu))
            }
        } ?: kotlin.run {
            value.add(ItemCheckoutViewModel(menu))
        }
        this.value = value
    }

    fun ArrayList<ItemCheckoutViewModel>.indexWithId(id: String?): Int? {
        var index: Int? = null
        this.mapIndexed { i, it ->
            if (it.data.id == id) {
                index = i
            }
        }
        return index
    }
}