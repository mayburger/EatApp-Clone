package com.mayburger.eatclone.util.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.databinding.ItemTagsBinding
import com.mayburger.eatclone.model.TagDataModel
import com.mayburger.eatclone.ui.adapters.MealAdapter
import com.mayburger.eatclone.ui.adapters.RestaurantAdapter
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMealViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemTagViewModel
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
        items: LiveData<ArrayList<ItemRestaurantViewModel>>
    ) {
        val adapter = recyclerView.adapter as RestaurantAdapter?
        if (adapter != null) {
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(it)
            }
        }
    }

    @BindingAdapter("tags")
    @JvmStatic
    fun addTagsItem(view: ViewGroup,tags:ArrayList<TagDataModel>?){
        view.removeAllViews()
        tags?.let {
            for (i in it) {
                val mLayoutInflater = LayoutInflater.from(view.context)
                val binding = ItemTagsBinding.inflate(mLayoutInflater, view, false)
                val itemViewModel =
                    ItemTagViewModel()
                itemViewModel.selected.set(!tags.contains(i))
                binding.viewModel = itemViewModel
                binding.name.text = i.name
                view.addView(binding.root)
            }
        }
    }

    @BindingAdapter("mealAdapter")
    @JvmStatic
    fun addMealItems(
        recyclerView: RecyclerView,
        items: LiveData<ArrayList<ItemMealViewModel>>
    ) {
        val adapter = recyclerView.adapter as MealAdapter?
        if (adapter != null) {
            adapter.clearItems()
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(it)
            }

        }
    }


}