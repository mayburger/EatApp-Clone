package com.mayburger.eatclone.ui.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityAdminRestaurantBinding
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.RestaurantAdapter
import com.mayburger.eatclone.ui.admin.create.CreateRestaurantActivity
import com.mayburger.eatclone.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_admin_restaurant.*
import javax.inject.Inject

@AndroidEntryPoint
class AdminActivity : BaseActivity<ActivityAdminRestaurantBinding, AdminViewModel>(),
    AdminNavigator, RestaurantAdapter.Callback{

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_admin_restaurant
    override val viewModel: AdminViewModel by viewModels()

    @Inject
    lateinit var layoutManager:LinearLayoutManager
    @Inject
    lateinit var adapter: RestaurantAdapter

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, AdminActivity::class.java))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        rvAdmin.adapter = adapter
        adapter.setListener(this)
        viewModel.restaurants.observe(this, Observer {
            it?.let { it1 -> adapter.addItems(it1) }
        })
        CreateRestaurantActivity.startActivity(this,isEditMode=false)
    }

    override fun onSelectedItem(restaurant: RestaurantDataModel) {
        CreateRestaurantActivity.startActivity(this,restaurant,true)
    }
}