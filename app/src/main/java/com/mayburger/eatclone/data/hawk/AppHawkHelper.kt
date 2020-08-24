package com.mayburger.eatclone.data.hawk

import com.orhanobut.hawk.Hawk
import javax.inject.Inject

class AppHawkHelper @Inject constructor() : HawkHelper {


    private val TAG = this.javaClass.simpleName

    companion object {
        private const val HAWK_KEY_IS_LOGGED_IN = "hawk_key_is_logged_in"
        private const val HAWK_KEY_REGION = "hawk_key_region"
    }

    override var isLoggedIn: Boolean
        get() = Hawk.get(HAWK_KEY_IS_LOGGED_IN,false)
        set(value) {
            Hawk.put(HAWK_KEY_IS_LOGGED_IN,value)
        }
    override var region: String
        get() = Hawk.get(HAWK_KEY_REGION,"")
        set(value) {
            Hawk.put(HAWK_KEY_REGION,value)
        }
}