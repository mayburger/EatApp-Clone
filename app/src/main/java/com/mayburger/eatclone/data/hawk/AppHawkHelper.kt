package com.mayburger.eatclone.data.hawk

import com.mayburger.eatclone.model.RegionDataModel
import com.mayburger.eatclone.model.UserDataModel
import com.orhanobut.hawk.Hawk
import javax.inject.Inject

class AppHawkHelper @Inject constructor() : HawkHelper {


    private val TAG = this.javaClass.simpleName

    companion object {
        private const val HAWK_KEY_IS_LOGGED_IN = "hawk_key_is_logged_in"
        private const val HAWK_KEY_REGION = "hawk_key_region"
        private const val HAWK_KEY_USER = "hawk_key_user"
    }

    override var isLoggedIn: Boolean
        get() = Hawk.get(HAWK_KEY_IS_LOGGED_IN,false)
        set(value) {
            Hawk.put(HAWK_KEY_IS_LOGGED_IN,value)
        }
    override var region: RegionDataModel
        get() = Hawk.get(HAWK_KEY_REGION,RegionDataModel())
        set(value) {
            Hawk.put(HAWK_KEY_REGION,value)
        }
    override var user: UserDataModel
        get() = Hawk.get(HAWK_KEY_USER,UserDataModel())
        set(value) {
            Hawk.put(HAWK_KEY_USER,value)
        }
}