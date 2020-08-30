package com.mayburger.eatclone.ui.main.reservations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentPageReservationsBinding
import com.mayburger.eatclone.ui.base.BaseFragment
import com.mayburger.eatclone.ui.onboarding.OnBoardingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_page_reservations.*
import javax.inject.Inject

@AndroidEntryPoint
class ReservationsPageFragment : BaseFragment<FragmentPageReservationsBinding, ReservationsPageViewModel>(),
    ReservationsPageNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_page_reservations
    override val viewModel: ReservationsPageViewModel by viewModels()

    @Inject
    lateinit var adapter:OnBoardingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        pager.adapter = adapter

    }


}