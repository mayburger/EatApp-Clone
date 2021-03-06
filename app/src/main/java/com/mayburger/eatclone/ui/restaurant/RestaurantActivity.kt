package com.mayburger.eatclone.ui.restaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityRestaurantBinding
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantActivity: BaseActivity<ActivityRestaurantBinding, RestaurantViewModel>(),
    RestaurantNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_restaurant
    override val viewModel: RestaurantViewModel by viewModels()

    companion object{
        const val EXTRA_RESTAURANT = "extra_restaurant"
        fun startActivity(context: Context,restaurant:RestaurantDataModel?=null){
            val intent = Intent(context, RestaurantActivity::class.java)
            intent.putExtra(EXTRA_RESTAURANT,restaurant)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val restaurant = intent?.getParcelableExtra<RestaurantDataModel>(EXTRA_RESTAURANT)
        var navigator = 0
        restaurant?.let {
            navigator = R.navigation.nav_restaurant_detail
        }?: kotlin.run {
            navigator = R.navigation.nav_restaurant_browse
        }
        val host = NavHostFragment.create(navigator)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment,host)
            .setPrimaryNavigationFragment(host)
            .commit()
    }
}