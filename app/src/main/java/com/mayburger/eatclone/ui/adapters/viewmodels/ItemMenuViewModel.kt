package com.mayburger.eatclone.ui.adapters.viewmodels

import androidx.databinding.ObservableField
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.model.events.MenuAddEvent
import com.mayburger.eatclone.model.events.MenuQuantityChangeEvent
import com.mayburger.eatclone.ui.base.BaseItemViewModel

class ItemMenuViewModel(val data: MenuDataModel) : BaseItemViewModel() {

    override fun onEvent(obj: Any) {
        when (obj) {
            is MenuQuantityChangeEvent -> {
                if (data.id == obj.menu.id) {
                    quantity.set(obj.menu.quantity)
                }
            }
            is MenuAddEvent ->{
                if(data.id == obj.menu.id){
                    onClickAdd()
                }
            }
        }
    }

    var navigator: Callback? = null

    interface Callback {
        fun onQuantityChanged(quantity: Int)
    }

    val price = ObservableField("${data.price} ${data.currency}")
    val quantity = ObservableField(0)

    fun onClickAdd() {
        quantity.set(quantity.get()?.plus(1))
        data.quantity = quantity.get()!!
        quantity.get()?.let { navigator?.onQuantityChanged(it) }
    }

    fun onClickOrder() {
        if (quantity.get() == 0) {
            onClickAdd()
        }
    }

    fun onClickRemove() {
        quantity.set(quantity.get()?.minus(1))
        data.quantity = quantity.get()!!
        quantity.get()?.let { navigator?.onQuantityChanged(it) }
    }
}