package com.mayburger.eatclone.ui.auth.login

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.ext.isLoginValid
import com.mayburger.eatclone.util.rx.SchedulerProvider
import kotlinx.coroutines.launch

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
        if (isLoginValid(
                email.get() ?: "",
                password.get() ?: ""
            )
        ) {
            navigator?.showLoading()
            viewModelScope.launch {
                try {
                    dataManager.signIn(email.get() ?: "", password.get() ?: "")
                    navigator?.onSuccessLogin()
                } catch (e: Exception) {
                    navigator?.onError(e.message)
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