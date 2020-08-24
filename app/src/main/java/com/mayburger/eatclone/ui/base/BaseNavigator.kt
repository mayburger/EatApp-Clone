package com.mayburger.eatclone.ui.base

import android.content.ContentResolver

interface BaseNavigator{

    fun showLoading()

    fun hideLoading()

    fun finishActivity()

    fun onError(message: String?)

    fun showSnackBar(message:String)

    fun getContentResolver(): ContentResolver?

}