package com.mayburger.eatclone.ui.restaurant.reserve

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentReserveBinding
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.HorizontalSelectionAdapter
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemReserveAvailableTimesViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemReserveDateViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemReserveGuestViewModel
import com.mayburger.eatclone.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_reserve.*

@AndroidEntryPoint
class ReserveFragment : BaseFragment<FragmentReserveBinding, ReserveViewModel>(),
    ReserveNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_reserve
    override val viewModel: ReserveViewModel by viewModels()

    companion object {
        const val ARG_RESTAURANT = "arg_restaurant"

        fun getBundle(restaurant:RestaurantDataModel):Bundle{
            return bundleOf(ARG_RESTAURANT to restaurant)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        initRestaurant()
        initDate()
        initGuest()
        initAvailableTimes()
    }

    fun initRestaurant() {
        viewModel.restaurant.set(arguments?.getSerializable(ARG_RESTAURANT) as RestaurantDataModel)
    }

    private fun initDate() {
        val dateAdapter =
            HorizontalSelectionAdapter<ItemReserveDateViewModel>()
        rvDates.adapter = dateAdapter
        var itemViewModels = viewModel.getItemReserveDateViewModel(0)
        dateAdapter.setListener(object :
            HorizontalSelectionAdapter.Callback<ItemReserveDateViewModel> {
            override fun onSelectedItem(position: Int, item: ItemReserveDateViewModel) {
                itemViewModels = viewModel.getItemReserveDateViewModel(position)
                viewModel.selectedDate.set(item.it)
                dateAdapter.setItems(itemViewModels)
            }
        })
        dateAdapter.setItems(itemViewModels)
    }

    private fun initGuest() {
        val guestAdapter =
            HorizontalSelectionAdapter<ItemReserveGuestViewModel>()
        rvGuests.adapter = guestAdapter
        var itemViewModels =
            viewModel.getItemReserveGuestViewModel(viewModel.selectedNumberOfGuests.get() ?: 0)
        guestAdapter.setListener(object :
            HorizontalSelectionAdapter.Callback<ItemReserveGuestViewModel> {
            override fun onSelectedItem(position: Int, item: ItemReserveGuestViewModel) {
                println("THis is clicked")
                itemViewModels = viewModel.getItemReserveGuestViewModel(position)
                viewModel.selectedNumberOfGuests.set(item.it)
                guestAdapter.setItems(itemViewModels)
            }
        })
        guestAdapter.setItems(itemViewModels)
    }

    private fun initAvailableTimes() {
        val timesAdapter =
            HorizontalSelectionAdapter<ItemReserveAvailableTimesViewModel>()
        rvTimes.adapter = timesAdapter
        var itemViewModels = viewModel.getItemReserveAvailableTimesViewModel( 0)
        viewModel.selectedTime.set(itemViewModels[0].it)
        timesAdapter.setListener(object :
            HorizontalSelectionAdapter.Callback<ItemReserveAvailableTimesViewModel> {
            override fun onSelectedItem(position: Int, item: ItemReserveAvailableTimesViewModel) {
                itemViewModels = viewModel.getItemReserveAvailableTimesViewModel(position)
                viewModel.selectedTime.set(item.it)
                timesAdapter.setItems(itemViewModels)
            }
        })
        timesAdapter.setItems(itemViewModels)
    }

    override fun onClickClose() {
        activity?.supportFragmentManager?.popBackStack()
    }


}