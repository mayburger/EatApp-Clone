package com.mayburger.eatclone.ui.order

import com.mayburger.eatclone.ui.base.BaseNavigator


interface CheckoutNavigator: BaseNavigator {
    fun onSuccessOrder()
}
