package com.mayburger.eatclone.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityOnBoardingBinding
import com.mayburger.eatclone.ui.auth.select.SelectAuthFragment
import com.mayburger.eatclone.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_on_boarding.*
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding, OnBoardingViewModel>(),
    OnBoardingNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_on_boarding
    override val viewModel: OnBoardingViewModel by viewModels()

    @Inject
    lateinit var adapter: OnBoardingAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    val pagerChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            viewModel.selectedPosition.set(position)
            subtitle.animate().alpha(0f).setDuration(500)
            .withEndAction{
                viewModel.subtitle.set(viewModel.dataManager.boardingData[position].subtitle)
                subtitle.animate().alpha(1f).setDuration(500).start()
            }.
            start()
            tvTitle.animate().alpha(0f).setDuration(500)
            .withEndAction{
                viewModel.title.set(viewModel.dataManager.boardingData[position].title)
                tvTitle.animate().alpha(1f).setDuration(500).start()
            }.
            start()
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, OnBoardingActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        pager.adapter = adapter
        pager.registerOnPageChangeCallback(pagerChangeCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        pager.unregisterOnPageChangeCallback(pagerChangeCallback)
    }

    override fun onGetStarted() {
        SelectAuthFragment.getInstance().show(supportFragmentManager, SelectAuthFragment.TAG)
    }
}