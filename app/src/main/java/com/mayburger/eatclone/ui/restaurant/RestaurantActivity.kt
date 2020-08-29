package com.mayburger.eatclone.ui.restaurant

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityRestaurantDetailBinding
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantActivity: BaseActivity<ActivityRestaurantDetailBinding, RestaurantViewModel>(),
    RestaurantNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_restaurant_detail
    override val viewModel: RestaurantViewModel by viewModels()

    companion object{
        const val EXTRA_RESTAURANT = "extra_restaurant"
        fun startActivity(context: Context,restaurant:RestaurantDataModel){
            val intent = Intent(context, RestaurantActivity::class.java)
            intent.putExtra(EXTRA_RESTAURANT,restaurant)
            context.startActivity(intent)
        }
    }
}