package com.mayburger.eatclone.ui.region

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivitySelectRegionBinding
import com.mayburger.eatclone.model.RegionDataModel
import com.mayburger.eatclone.model.events.SelectRegionEvent
import com.mayburger.eatclone.ui.base.BaseActivity
import com.mayburger.eatclone.util.rx.RxBus
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
        viewDataBinding.lifecycleOwner = this
        rvRegions.adapter = adapter
        adapter.setListener(this)
        rvRegions.isNestedScrollingEnabled = false
    }

    override fun onSelectedItem(region: RegionDataModel) {
        viewModel.setRegion(region)
    }

    override fun onSelectRegion() {
        RxBus.getDefault().send(SelectRegionEvent())
        finish()
    }
}
