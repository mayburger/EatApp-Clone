package com.mayburger.eatclone.ui.menu.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentMenuBinding
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.MenuAdapter
import com.mayburger.eatclone.ui.base.BaseFragment
import com.mayburger.eatclone.ui.menu.detail.MenuDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_menu.*
import javax.inject.Inject


@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>(),
    MenuNavigator, MenuAdapter.Callback {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_menu
    override val viewModel: MenuViewModel by viewModels()

    @Inject
    lateinit var menusAdapter: MenuAdapter

    companion object {
        const val ARG_RESTAURANT = "arg_restaurant"
        fun getBundle(restaurant: RestaurantDataModel): Bundle {
            return bundleOf(ARG_RESTAURANT to restaurant)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        viewDataBinding?.lifecycleOwner = viewLifecycleOwner
        initRestaurant()
        initMenus()
    }

    fun initMenus() {
        rvMenu.adapter = menusAdapter
        menusAdapter.setListener(this)
    }

    fun initRestaurant() {
        viewModel.restaurant.set(arguments?.getSerializable(ARG_RESTAURANT) as RestaurantDataModel)
    }

    override fun onSelectedItem(menu: MenuDataModel, position: Int) {
        val fragment = MenuDetailFragment()
        fragment.arguments = MenuDetailFragment.getBundle(menu, position)
        fragment.show(requireActivity().supportFragmentManager, "TAG")
    }
}
