package com.mayburger.eatclone.ui.region

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivitySelectRegionBinding
import com.mayburger.eatclone.model.RegionDataModel
import com.mayburger.eatclone.ui.base.BaseActivity
import com.mayburger.eatclone.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_select_region.*
import javax.inject.Inject

@AndroidEntryPoint
class SelectRegionActivity : BaseActivity<ActivitySelectRegionBinding, SelectRegionViewModel>(),
    SelectRegionNavigator,SelectRegionAdapter.Callback{

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_select_region
    override val viewModel: SelectRegionViewModel by viewModels()

    @Inject
    lateinit var adapter:SelectRegionAdapter

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, SelectRegionActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        rvRegions.adapter = adapter
        adapter.setListener(this)
        rvRegions.isNestedScrollingEnabled = false
        viewModel.getRegions()
    }

    override fun onSelectedItem(region: RegionDataModel) {
        viewModel.setRegion(region)
    }

    override fun onLoadRegion() {
        rvRegions.layoutManager = GridLayoutManager(this,2)
    }

    override fun onSelectRegion() {
        MainActivity.startActivity(this)
        finish()
    }
}
