package com.mayburger.eatclone.ui.admin.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityCreateRestaurantBinding
import com.mayburger.eatclone.databinding.ItemTagsBinding
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.ItemTagViewModel
import com.mayburger.eatclone.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_create_restaurant.*

@AndroidEntryPoint
class CreateRestaurantActivity : BaseActivity<ActivityCreateRestaurantBinding, CreateRestaurantViewModel>(),
    CreateRestaurantNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_create_restaurant
    override val viewModel: CreateRestaurantViewModel by viewModels()

    companion object{
        private const val EXTRA_RESTAURANT = "extra_restaurant"
        private const val EXTRA_EDIT_MODE = "edit_mode"
        fun startActivity(context: Context,restaurant:RestaurantDataModel?=null,isEditMode:Boolean){
            val intent = Intent(context, CreateRestaurantActivity::class.java)
            if (restaurant != null){
                intent.putExtra(EXTRA_RESTAURANT,restaurant)
            }
            intent.putExtra(EXTRA_EDIT_MODE,isEditMode)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        viewModel.getTags(this)

        if(intent.getBooleanExtra(EXTRA_EDIT_MODE,false)){
            initEditMode()
            viewModel.isEditMode.set(true)
        }
        initTags()
    }

    fun initTags(){
        flex.removeAllViews()
        for (i in viewModel.tags){
            val mLayoutInflater = LayoutInflater.from(this)
            val binding = ItemTagsBinding.inflate(mLayoutInflater, flex,false)
            val name = binding.root.findViewById<TextView>(R.id.name)
            val itemViewModel = ItemTagViewModel()
            itemViewModel.navigator = object:ItemTagViewModel.Callback{
                override fun onClickTag() {
                    itemViewModel.selected.set(itemViewModel.selected.get()?.not())
                    if (viewModel.selectedTags.contains(i)){
                        viewModel.selectedTags.remove(i)
                    } else{
                        viewModel.selectedTags.add(i)
                    }
                }
            }
            itemViewModel.selected.set(!viewModel.selectedTags.contains(i))
            binding.viewModel = itemViewModel
            name.text = i.name
            flex.addView(binding.root)
        }
    }

    fun initEditMode(){
        val i = intent.getSerializableExtra(EXTRA_RESTAURANT) as RestaurantDataModel
        viewModel.id.set(i.id)
        viewModel.name.set(i.name)
        viewModel.address.set(i.address)
        viewModel.image.set(i.image)
        viewModel.cuisine.set(i.cuisine)
        viewModel.distance.set(i.distance)
        viewModel.selectedPrice.set(i._price)
        for (ist in i.tags!!){
            println("This ${ist.name}")
        }
        i.tags.let { it?.let { it1 -> viewModel.selectedTags.addAll(it1) } }
        initTags()
    }

}