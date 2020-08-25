package com.mayburger.eatclone.util.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.ui.region.ItemRegionViewModel
import com.mayburger.eatclone.ui.region.SelectRegionAdapter
import com.mayburger.eatclone.ui.restaurant.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.restaurant.RestaurantAdapter

object AdapterBinding {

    @BindingAdapter("regionAdapter")
    @JvmStatic
    fun addRegionItems(
        recyclerView: RecyclerView,
        items: ArrayList<ItemRegionViewModel>
    ) {
        val adapter = recyclerView.adapter as SelectRegionAdapter?
        if (adapter != null) {
            adapter.clearItems()
            adapter.addItems(items)
        }
    }

    @BindingAdapter("restaurantAdapter")
    @JvmStatic
    fun addAdminRestaurantItems(
        recyclerView: RecyclerView,
        items: ArrayList<ItemRestaurantViewModel>
    ) {
        val adapter = recyclerView.adapter as RestaurantAdapter?
        if (adapter != null) {
            adapter.clearItems()
            adapter.addItems(items)
        }
    }

}