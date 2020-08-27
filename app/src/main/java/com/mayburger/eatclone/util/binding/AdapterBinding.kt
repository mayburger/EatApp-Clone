package com.mayburger.eatclone.util.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.ui.adapters.MealAdapter
import com.mayburger.eatclone.ui.adapters.RestaurantAdapter
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMealViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.region.ItemRegionViewModel
import com.mayburger.eatclone.ui.region.SelectRegionAdapter

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
    fun addRestaurantItems(
        recyclerView: RecyclerView,
        items: ArrayList<ItemRestaurantViewModel>
    ) {
        val adapter = recyclerView.adapter as RestaurantAdapter?
        if (adapter != null) {
            adapter.clearItems()
            adapter.addItems(items)
        }
    }

    @BindingAdapter("mealAdapter")
    @JvmStatic
    fun addMealItems(
        recyclerView: RecyclerView,
        items: ArrayList<ItemMealViewModel>
    ) {
        val adapter = recyclerView.adapter as MealAdapter?
        if (adapter != null) {
            adapter.clearItems()
            adapter.addItems(items)
        }
    }



}