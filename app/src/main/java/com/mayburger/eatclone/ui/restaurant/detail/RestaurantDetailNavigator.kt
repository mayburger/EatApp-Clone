package com.mayburger.eatclone.ui.restaurant.detail

import com.mayburger.eatclone.ui.base.BaseNavigator


interface RestaurantDetailNavigator: BaseNavigator {
    fun onClickReserve()
    fun onClickMaps()
    fun onClickCall()
    fun onClickTripAdvisor()
}
