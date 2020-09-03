package com.mayburger.eatclone.ui.restaurant.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.google.android.material.appbar.AppBarLayout
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentRestaurantDetailBinding
import com.mayburger.eatclone.databinding.ItemOpenHoursBinding
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.RestaurantAdapter
import com.mayburger.eatclone.ui.base.BaseFragment
import com.mayburger.eatclone.ui.reserve.ReserveFragment
import com.mayburger.eatclone.ui.restaurant.RestaurantActivity
import com.mayburger.eatclone.util.ext.setReadMore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*
import javax.inject.Inject
import kotlin.math.abs


@AndroidEntryPoint
class RestaurantDetailFragment :
    BaseFragment<FragmentRestaurantDetailBinding, RestaurantDetailViewModel>(),
    RestaurantDetailNavigator, RestaurantAdapter.Callback {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_restaurant_detail
    override val viewModel: RestaurantDetailViewModel by viewModels()

    @Inject
    lateinit var restaurantAdapter: RestaurantAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.lifecycleOwner = viewLifecycleOwner
        navController = Navigation.findNavController(view)
        buildDetail()
        buildHours()
        buildAppbar()
        buildOthers()
    }

    fun buildDetail() {
        viewModel.restaurant.set(activity?.intent?.getParcelableExtra(RestaurantActivity.EXTRA_RESTAURANT))
        viewModel.buildVisibility()
        viewModel.navigator = this
        notes.text = viewModel.restaurant.get()?.notes
        notes.setReadMore(rootNotes, viewModel.showReadMore, viewModel.notesMaxLine)
    }

    fun buildOthers() {
        rvOthers.adapter = restaurantAdapter
        restaurantAdapter.setListener(this)
        restaurantAdapter.asImage()
    }


    private fun buildAppbar() {
        startAlphaAnimation(titleText, 0, View.INVISIBLE)
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout: AppBarLayout, i: Int ->
            val maxScroll = appBarLayout.totalScrollRange
            val percentage = abs(i) / maxScroll.toFloat()
            handleToolbarTitleVisibility(percentage, titleText)
        })
    }

    private fun handleToolbarTitleVisibility(
        percentage: Float,
        view: View
    ) {
        val percentageToShowTitleAtToolbar = 0.9f
        val alphaAnimationDuration = 100f
        if (percentage >= percentageToShowTitleAtToolbar) {
            if (viewModel.mIsTheTitleVisible.get()?.not() == true) {
                startAlphaAnimation(
                    view,
                    alphaAnimationDuration.toLong(),
                    View.VISIBLE
                )
                back.setImageResource(R.drawable.ic_back_black)
                viewModel.mIsTheTitleVisible.set(true)
            }
        } else {
            if (viewModel.mIsTheTitleVisible.get() == true) {
                startAlphaAnimation(
                    view,
                    alphaAnimationDuration.toLong(),
                    View.INVISIBLE
                )
                viewModel.mIsTheTitleVisible.set(false)
                back.setImageResource(R.drawable.ic_back_white)
            }
        }
    }

    private fun startAlphaAnimation(
        v: View,
        duration: Long,
        visibility: Int
    ) {
        val alphaAnimation =
            if (visibility == View.VISIBLE) AlphaAnimation(
                0f,
                1f
            ) else AlphaAnimation(1f, 0f)
        alphaAnimation.duration = duration
        alphaAnimation.fillAfter = true
        v.startAnimation(alphaAnimation)
    }


    private fun buildHours() {
        hours.removeAllViews()
        for (i in 0..6) {
            val mLayoutInflater = LayoutInflater.from(activity)
            val binding = ItemOpenHoursBinding.inflate(mLayoutInflater, hours, false)
            if (i == 4) {
                binding.abbr.setBackgroundResource(R.drawable.shp_primary_8dp)
                binding.abbrText.setTextColor(resources.getColor(R.color.colorWhite))
                binding.day.setTextColor(resources.getColor(R.color.colorPrimary))
                binding.time.setTextColor(resources.getColor(R.color.colorPrimary))
            }
            hours.addView(binding.root)
        }
    }

    override fun onClickReserve() {
        val extras = FragmentNavigatorExtras(
            name to "restaurantName"
        )
        navController.navigate(
            R.id.action_restaurantDetailFragment_to_reserveFragment,
            ReserveFragment.getBundle(viewModel.restaurant.get() ?: RestaurantDataModel()),
            null,
            extras
        )
    }

    override fun onClickMaps() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://maps.google.com/maps?q=${viewModel.restaurant.get()?.name}")
        )
        startActivity(intent)
    }

    override fun onClickCall() {

    }

    override fun onClickTripAdvisor() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.tripadvisor.com/Search?q=${viewModel.restaurant.get()?.name}")
        )
        startActivity(intent)
    }

    override fun onSelectedItem(restaurant: RestaurantDataModel) {
        activity?.let { RestaurantActivity.startActivity(it, restaurant) }
    }
}