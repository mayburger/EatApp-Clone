package com.mayburger.eatclone.data

import com.mayburger.eatclone.data.firebase.FirebaseHelper
import com.mayburger.eatclone.data.hawk.HawkHelper
import com.mayburger.eatclone.model.OnBoardingModel
import java.util.*
import kotlin.collections.ArrayList

interface DataManager : HawkHelper, FirebaseHelper {
    val boardingData: ArrayList<OnBoardingModel>

    fun get30Days(): ArrayList<Date>
    fun getAvailableTimes():ArrayList<Date>
}