package com.mayburger.eatclone.ui.auth.select

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentSelectAuthBinding
import com.mayburger.eatclone.ui.auth.login.LoginActivity
import com.mayburger.eatclone.ui.auth.register.RegisterActivity
import com.mayburger.eatclone.ui.base.BaseBSDFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectAuthFragment : BaseBSDFragment<FragmentSelectAuthBinding, SelectAuthViewModel>(),SelectAuthNavigator{

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_select_auth
    override val viewModel: SelectAuthViewModel by viewModels()

    companion object {
         const val TAG = "SelectAuthFragment"
        fun getInstance() = SelectAuthFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
    }

    override fun onClickLogin() {
        dismiss()
        context?.let { LoginActivity.startActivity(it) }
    }

    override fun onClickRegister() {
        dismiss()
        context?.let { RegisterActivity.startActivity(it) }
    }
}