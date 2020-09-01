package com.mayburger.eatclone.ui.adapters.viewmodels

import androidx.databinding.ObservableField
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.model.events.MenuQuantityChangeEvent
import com.mayburger.eatclone.ui.base.BaseItemViewModel

class ItemMenuViewModel(val data: MenuDataModel) : BaseItemViewModel() {

    override fun onEvent(obj: Any) {
        when (obj) {
            is MenuQuantityChangeEvent -> {
                if (data.id == obj.id) {
                    onClickAdd()
                }
            }
        }
    }

    val showContent = ObservableField(false)
    val price = ObservableField("${data.price} ${data.currency}")
    val quantity = ObservableField(0)

    fun onClickAdd() {
        quantity.set(quantity.get()?.plus(1))
    }

    fun onClickOrder() {
        if (quantity.get() == 0) {
            onClickAdd()
        }
    }

    fun onClickRemove() {
        quantity.set(quantity.get()?.minus(1))
    }
}