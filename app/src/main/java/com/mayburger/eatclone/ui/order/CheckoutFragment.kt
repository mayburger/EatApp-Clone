package com.mayburger.eatclone.ui.order

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentCheckoutBinding
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.model.events.MenuQuantityChangeEvent
import com.mayburger.eatclone.ui.adapters.CheckoutAdapter
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.base.BaseBSDFragment
import com.mayburger.eatclone.util.rx.RxBus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_checkout.*
import javax.inject.Inject


@AndroidEntryPoint
class CheckoutFragment : BaseBSDFragment<FragmentCheckoutBinding, CheckoutViewModel>(),
    CheckoutNavigator, CheckoutAdapter.Callback {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_checkout
    override val viewModel: CheckoutViewModel by viewModels()

    @Inject
    lateinit var checkoutAdapter: CheckoutAdapter

    companion object {
        const val ARG_MENU = "arg_menu"
        const val ARG_RESTAURANT = "arg_restaurant"
        fun getBundle(menus: ArrayList<MenuDataModel>, restaurant: RestaurantDataModel): Bundle {
            return bundleOf(ARG_MENU to menus, ARG_RESTAURANT to restaurant)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        viewDataBinding?.lifecycleOwner = viewLifecycleOwner

        val menus: ArrayList<MenuDataModel>? = arguments?.getParcelableArrayList(ARG_MENU)
        val restaurant: RestaurantDataModel? = arguments?.getParcelable(ARG_RESTAURANT)
        viewModel.addMenus(menus)
        viewModel.restaurant.set(ItemRestaurantViewModel(restaurant ?: RestaurantDataModel()))
        initCheckout()
    }

    fun initCheckout() {
        rvCheckout.adapter = checkoutAdapter
        checkoutAdapter.setListener(this)
        viewModel.menus.observe(viewLifecycleOwner, Observer {
            var subtotal = 0
            val tax: Int
            it.map { subtotal += (it.data.price * it.data.quantity) }
            tax = ((subtotal * 10) / 100)
            viewModel.subtotal.postValue("${subtotal} AED")
            viewModel.tax.postValue("${tax} AED")
            viewModel.total.postValue("${subtotal + tax} AED")
            if (it.isEmpty()) {
                activity?.onBackPressed()
            }
        })
    }

    override fun onSelectedItem(menu: MenuDataModel, position: Int) {

    }

    override fun onQuantityChanged(menu: MenuDataModel) {
        RxBus.getDefault().send(MenuQuantityChangeEvent(menu))
    }
}