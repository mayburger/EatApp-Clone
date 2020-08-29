package com.mayburger.eatclone.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityMainBinding
import com.mayburger.eatclone.ui.base.BaseActivity
import com.mayburger.eatclone.ui.main.explore.ExploreFragment
import com.mayburger.eatclone.ui.main.more.MoreFragment
import com.mayburger.eatclone.ui.main.reservations.ReservationsFragment
import com.mayburger.eatclone.ui.main.support.SupportFragment
import com.mayburger.eatclone.util.ActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


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
        setUpNavigation()
    }

    override fun onBackPressed() {
        finish()
    }

    fun setUpNavigation() {
        ActivityUtil.loadFragment(R.id.container, supportFragmentManager, ExploreFragment())
        nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.explore->{
                    ActivityUtil.loadFragment(R.id.container, supportFragmentManager, ExploreFragment())
                }
                R.id.reservations->{
                    ActivityUtil.loadFragment(R.id.container, supportFragmentManager, ReservationsFragment())
                }
                R.id.support->{
                    ActivityUtil.loadFragment(R.id.container, supportFragmentManager, SupportFragment())
                }
                R.id.more->{
                    ActivityUtil.loadFragment(R.id.container, supportFragmentManager, MoreFragment())
                }
            }
            true
        }
    }

}