package com.mayburger.eatclone.ui.menu.detail

import com.mayburger.eatclone.ui.base.BaseNavigator


interface MenuDetailNavigator: BaseNavigator {
    fun onChangeQuantity(quantity:Int)
}
