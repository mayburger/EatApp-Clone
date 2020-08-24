package com.mayburger.eatclone.ui.auth.login

import com.mayburger.eatclone.ui.base.BaseNavigator


interface LoginNavigator: BaseNavigator {
    fun onClickRegister()
    fun onSuccessLogin()
}
