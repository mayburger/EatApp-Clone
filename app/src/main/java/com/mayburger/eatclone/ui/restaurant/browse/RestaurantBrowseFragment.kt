package com.mayburger.eatclone.ui.restaurant.browse

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentRestaurantBrowseBinding
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.RestaurantAdapter
import com.mayburger.eatclone.ui.base.BaseFragment
import com.mayburger.eatclone.ui.restaurant.RestaurantActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_restaurant_browse.*
import javax.inject.Inject


@AndroidEntryPoint
class RestaurantBrowseFragment :
    BaseFragment<FragmentRestaurantBrowseBinding, RestaurantBrowseViewModel>(),
    RestaurantBrowseNavigator,RestaurantAdapter.Callback{

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_restaurant_browse
    override val viewModel: RestaurantBrowseViewModel by viewModels()

    @Inject
    lateinit var restaurantAdapter: RestaurantAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.lifecycleOwner = viewLifecycleOwner
        initRestaurant()
    }

    fun initRestaurant(){
        rvRestaurants.adapter = restaurantAdapter
        restaurantAdapter.setListener(this)
    }

    override fun onSelectedItem(restaurant: RestaurantDataModel) {
        RestaurantActivity.startActivity(requireActivity(),restaurant)
    }
}