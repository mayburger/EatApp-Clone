package com.mayburger.eatclone.ui.auth.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityRegisterBinding
import com.mayburger.eatclone.ui.auth.login.LoginActivity
import com.mayburger.eatclone.ui.base.BaseActivity
import com.mayburger.eatclone.util.ViewUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(),
    RegisterNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_register
    override val viewModel: RegisterViewModel by viewModels()

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
    }

    override fun onClickLogin() {
        LoginActivity.startActivity(this)
        finish()
    }

    override fun onSuccessRegister() {
        val dialog = ViewUtils.getDialog(
            this,
            "Success!",
            "Your account has been created, please Login to get started!",
            true,
            "Okay",
            Runnable {
                LoginActivity.startActivity(this)
                finish()
            },
            null,
            null
        )
        dialog.show()
        dialog.setOnDismissListener {
            LoginActivity.startActivity(this)
            finish()
        }
    }

}