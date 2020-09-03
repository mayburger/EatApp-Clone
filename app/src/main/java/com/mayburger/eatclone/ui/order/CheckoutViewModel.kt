package com.mayburger.eatclone.ui.order

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.model.OrderDataModel
import com.mayburger.eatclone.model.events.MenuQuantityChangeEvent
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemCheckoutViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    var order = OrderDataModel()

    fun addMenus(data: ArrayList<MenuDataModel>?) {
        menus.postValue(ArrayList<ItemCheckoutViewModel>().apply {
            data?.map {
                add(
                    ItemCheckoutViewModel(it)
                )
            }
        })
    }

     fun onClickOrder(){
         navigator?.showLoading()
        viewModelScope.launch(Dispatchers.IO){
            try{
                val hasOngoingOrder = dataManager.hasOngoingOrder()
                if (hasOngoingOrder){
                    navigator?.onError("You still have an ongoing order! Please cancel or finish your order before ordering a new one")
                    navigator?.hideLoading()
                } else{
                    submitOrder()
                }
            } catch (e:Exception){
                navigator?.onError(e.message)
                navigator?.hideLoading()
            }
        }
    }

    suspend fun submitOrder(){
        try{
            val create = dataManager.createOrder(order)
            order.id = create?.id
            dataManager.updateOrder(order)
            navigator?.hideLoading()
            navigator?.onSuccessOrder()
        }catch (e:Exception){
            navigator?.onError(e.message)
            navigator?.hideLoading()
        }
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