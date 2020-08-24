package com.mayburger.eatclone.ui.region

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivitySelectRegionBinding
import com.mayburger.eatclone.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectRegionActivity : BaseActivity<ActivitySelectRegionBinding, SelectRegionViewModel>(),
    SelectRegionNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_select_region
    override val viewModel: SelectRegionViewModel by viewModels()

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, SelectRegionActivity::class.java))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
