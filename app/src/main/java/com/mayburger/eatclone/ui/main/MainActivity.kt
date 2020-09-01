package com.mayburger.eatclone.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityMainBinding
import com.mayburger.eatclone.ui.base.BaseActivity
import com.mayburger.eatclone.ui.main.explore.ExploreFragment
import com.mayburger.eatclone.ui.main.more.MoreFragment
import com.mayburger.eatclone.ui.main.reservations.ReservationsPageFragment
import com.mayburger.eatclone.ui.main.support.SupportFragment
import com.mayburger.eatclone.ui.region.SelectRegionActivity
import com.mayburger.eatclone.ui.scan.ScanActivity
import com.mayburger.eatclone.ui.search.SearchActivity
import com.mayburger.eatclone.util.ActivityUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        viewDataBinding.lifecycleOwner = this
        if (viewModel.dataManager.region.id == null) {
            SelectRegionActivity.startActivity(this)
        }
        setUpNavigation()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onClickScan() {
        ScanActivity.startActivity(this)
    }

    override fun onClickSearch() {
        SearchActivity.startActivity(this)
    }

    override fun onClickRegion() {
        SelectRegionActivity.startActivity(this)
    }

    fun setUpNavigation() {
        viewModel.selectedBottomNav.observe(this, Observer {
            when (it) {
                0 -> {
                    loadFragment(ExploreFragment())
                    viewModel.selectedBottomNavTitle.set("Explore")
                }
                1 -> {
                    loadFragment(ReservationsPageFragment())
                    viewModel.selectedBottomNavTitle.set("Reservations")
                }
                2 -> {
                    loadFragment(SupportFragment())
                    viewModel.selectedBottomNavTitle.set("Support")
                }
                3 -> {
                    loadFragment(MoreFragment())
                    viewModel.selectedBottomNavTitle.set("More")
                }
            }
        })
    }

    fun loadFragment(fragment: Fragment) {
        ActivityUtil.loadFragment(
            R.id.container,
            supportFragmentManager,
            fragment
        )
    }

}