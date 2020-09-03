package com.mayburger.eatclone.ui.menu.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentMenuDetailBinding
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.model.events.MenuAddEvent
import com.mayburger.eatclone.ui.base.BaseBSDFragment
import com.mayburger.eatclone.util.rx.RxBus
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MenuDetailFragment : BaseBSDFragment<FragmentMenuDetailBinding, MenuDetailViewModel>(),
    MenuDetailNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_menu_detail
    override val viewModel: MenuDetailViewModel by viewModels()


    companion object {
        const val ARG_MENU = "arg_menu"
        const val ARG_POSITION = "arg_position"
        fun getBundle(restaurant: MenuDataModel,position:Int): Bundle {
            return bundleOf(ARG_MENU to restaurant, ARG_POSITION to position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        viewDataBinding?.lifecycleOwner = viewLifecycleOwner
        initMenu()
    }

    override fun onChangeQuantity(menu: MenuDataModel) {
        RxBus.getDefault().send(viewModel.menu.get()?.id?.let { MenuAddEvent(menu) })
        dismiss()
    }

    fun initMenu() {
        viewModel.menu.set(arguments?.getParcelable(ARG_MENU))
        viewModel.setPrice()
    }
}