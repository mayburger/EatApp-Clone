package com.mayburger.eatclone.ui.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.data.events.LoginEvent
import com.mayburger.eatclone.databinding.ActivityLoginBinding
import com.mayburger.eatclone.ui.auth.register.RegisterActivity
import com.mayburger.eatclone.ui.base.BaseActivity
import com.mayburger.eatclone.ui.splash.SplashActivity
import com.mayburger.eatclone.util.rx.RxBus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivityLoginBinding, LoginViewModel>(),
    LoginNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModels()

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
    }

    override fun onClickRegister() {
        RegisterActivity.startActivity(this)
        finish()
    }

    override fun onSuccessLogin() {
        RxBus.getDefault().send(LoginEvent())
        SplashActivity.startActivity(this)
        finish()
    }

}