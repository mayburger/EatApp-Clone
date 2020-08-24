package com.mayburger.eatclone.ui.auth.register

import com.mayburger.eatclone.ui.base.BaseNavigator


interface RegisterNavigator: BaseNavigator {
    fun onClickLogin()
    fun onSuccessRegister()
}
