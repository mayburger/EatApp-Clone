package com.mayburger.eatclone.ui.success

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentSuccessBinding
import com.mayburger.eatclone.ui.base.BaseBSDFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SuccessFragment : BaseBSDFragment<FragmentSuccessBinding, SuccessViewModel>(),
    SuccessNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_success
    override val viewModel: SuccessViewModel by viewModels()


    companion object {
        const val ARG_TITLE = "arg_title"
        const val ARG_SUBTITLE = "arg_subtitle"
        fun getBundle(title:String,subtitle:String): Bundle {
            return bundleOf(ARG_TITLE to title, ARG_SUBTITLE to subtitle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        viewDataBinding?.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        finishActivity()
    }
}