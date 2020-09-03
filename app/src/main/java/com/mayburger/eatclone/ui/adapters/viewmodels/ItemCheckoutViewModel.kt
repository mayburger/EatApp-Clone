package com.mayburger.eatclone.ui.adapters.viewmodels

import androidx.databinding.ObservableField
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.ui.base.BaseItemViewModel

class ItemCheckoutViewModel(val data: MenuDataModel) : BaseItemViewModel() {

    override fun onEvent(obj: Any) {
    }

    var navigator:Callback? = null

    interface Callback{
        fun onQuantityChanged(quantity:Int)
    }

    val price = ObservableField("${data.price * data.quantity} ${data.currency}")
    val quantity = ObservableField(data.quantity)

    fun onClickAdd() {
        quantity.set(quantity.get()?.plus(1))
        data.quantity = quantity.get()!!
        quantity.get()?.let { navigator?.onQuantityChanged(it) }
    }

    fun onClickRemove() {
        quantity.set(quantity.get()?.minus(1))
        data.quantity = quantity.get()!!
        quantity.get()?.let { navigator?.onQuantityChanged(it) }
    }
}