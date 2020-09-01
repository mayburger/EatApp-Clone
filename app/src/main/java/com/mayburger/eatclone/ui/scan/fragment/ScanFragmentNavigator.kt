package com.mayburger.eatclone.ui.scan.fragment

import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseNavigator


interface ScanFragmentNavigator: BaseNavigator {
    fun onClickRestaurant(it:RestaurantDataModel)
}
