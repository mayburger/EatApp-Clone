package com.mayburger.eatclone.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityMainBinding
import com.mayburger.eatclone.model.MealDataModel
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.MealAdapter
import com.mayburger.eatclone.ui.adapters.RestaurantAdapter
import com.mayburger.eatclone.ui.base.BaseActivity
import com.mayburger.eatclone.ui.restaurant.RestaurantActivity
import com.mayburger.eatclone.ui.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainNavigator, MealAdapter.Callback, RestaurantAdapter.Callback {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var restaurantAdapter: RestaurantAdapter

    @Inject
    lateinit var collectionAdapter: RestaurantAdapter

    @Inject
    lateinit var mealsAdapter: MealAdapter

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        initSort()
        initMeals()
        initCollection()
    }

    fun initMeals() {
        viewModel.getMeals()
        rvMeal.adapter = mealsAdapter
        mealsAdapter.setListener(this)

    }

    fun initSort() {
        viewModel.getRestaurants()
        rvSort.adapter = restaurantAdapter
        restaurantAdapter.setListener(this)
        restaurantAdapter.asGrid()
    }

    fun initCollection() {
        rvCollection.adapter = collectionAdapter
        collectionAdapter.setListener(this)
        collectionAdapter.asCollection()
    }

    override fun onClickSearch() {
        SearchActivity.startActivity(this)
    }

    override fun onSelectedItem(restaurant: RestaurantDataModel) {
        RestaurantActivity.startActivity(this@MainActivity, restaurant)
    }

    override fun onSelectedItem(restaurant: MealDataModel) {

    }

}