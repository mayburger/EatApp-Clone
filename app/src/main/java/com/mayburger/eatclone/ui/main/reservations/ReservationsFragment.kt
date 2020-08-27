package com.mayburger.eatclone.ui.main.reservations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentReservationsBinding
import com.mayburger.eatclone.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationsFragment : BaseFragment<FragmentReservationsBinding, ReservationsViewModel>(),
    ReservationsNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_reservations
    override val viewModel: ReservationsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
    }
}