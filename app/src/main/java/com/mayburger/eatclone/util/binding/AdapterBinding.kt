package com.mayburger.eatclone.util.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ItemTagsBinding
import com.mayburger.eatclone.model.TagDataModel
import com.mayburger.eatclone.ui.adapters.CategoryAdapter
import com.mayburger.eatclone.ui.adapters.RestaurantAdapter
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemCategoryViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemTagViewModel
import com.mayburger.eatclone.ui.region.ItemRegionViewModel
import com.mayburger.eatclone.ui.region.SelectRegionAdapter
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMenuViewModel
import com.mayburger.eatclone.ui.adapters.MenuAdapter

object AdapterBinding {

    @BindingAdapter("regionAdapter")
    @JvmStatic
    fun addRegionItems(
        recyclerView: RecyclerView,
        items: LiveData<ArrayList<ItemRegionViewModel>>
    ) {
        val adapter = recyclerView.adapter as SelectRegionAdapter?
        if (adapter != null) {
            items.value?.let{
                adapter.clearItems()
                adapter.addItems(it)
            }
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

    @BindingAdapter("categoryAdapter")
    @JvmStatic
    fun addCategoryItems(
        recyclerView: RecyclerView,
        items: LiveData<ArrayList<ItemCategoryViewModel>>
    ) {
        val adapter = recyclerView.adapter as CategoryAdapter?
        if (adapter != null) {
            adapter.clearItems()
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(it)
            }

        }
    }

    @BindingAdapter("menuAdapter")
    @JvmStatic
    fun addMenuitems(
        recyclerView: RecyclerView,
        items: LiveData<ArrayList<ItemMenuViewModel>>
    ) {
        val adapter = recyclerView.adapter as MenuAdapter?
        if (adapter != null) {
            adapter.clearItems()
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(it)
            }
            val context = recyclerView.context;
            val controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
            recyclerView.layoutAnimation = controller;
            recyclerView.scheduleLayoutAnimation()

        }
    }


}