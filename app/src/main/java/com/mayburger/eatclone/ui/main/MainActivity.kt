package com.mayburger.eatclone.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityMainBinding
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseActivity
import com.mayburger.eatclone.ui.restaurant.RestaurantAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainNavigator,RestaurantAdapter.Callback{

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var restaurantAdapter:RestaurantAdapter
    @Inject
    lateinit var layoutManager:LinearLayoutManager

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        initSort()
    }

    fun initSort(){
        viewModel.getRestaurants()
        rvSort.adapter = restaurantAdapter
        rvSort.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        restaurantAdapter.setListener(this)
        restaurantAdapter.asGrid()
    }

    override fun onSelectedItem(restaurant: RestaurantDataModel) {

    }

}