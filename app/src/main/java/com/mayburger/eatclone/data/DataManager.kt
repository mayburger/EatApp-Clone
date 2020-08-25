package com.mayburger.eatclone.data

import com.mayburger.eatclone.data.firebase.FirebaseHelper
import com.mayburger.eatclone.data.hawk.HawkHelper
import com.mayburger.eatclone.model.OnBoardingModel

interface DataManager : HawkHelper,FirebaseHelper{
    val boardingData:ArrayList<OnBoardingModel>
}