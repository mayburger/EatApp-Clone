package com.mayburger.eatclone.ui.auth.login

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.google.firebase.auth.FirebaseAuth
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.isLoginValid
import com.mayburger.eatclone.util.rx.SchedulerProvider

class LoginViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<LoginNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {

    }

    val email = ObservableField("")
    val password = ObservableField("")

    fun onClickLogin() {
        if (isLoginValid(email.get() ?: "", password.get() ?: "")) {
            navigator?.showLoading()
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email.get() ?: "", password.get() ?: "")
                .addOnCompleteListener {
                    navigator?.hideLoading()
                    if (it.isSuccessful) {
                        navigator?.onSuccessLogin()
                    } else {
                        navigator?.onError(it.exception?.message)
                    }
                }
        } else {
            navigator?.onError("Please make sure all the fields are filled and conditions are met")
        }
    }

    fun onClickRegister() {
        navigator?.onClickRegister()
    }


}