package com.mayburger.eatclone.data

import com.mayburger.eatclone.data.firebase.FirebaseHelper
import com.mayburger.eatclone.data.hawk.HawkHelper
import com.mayburger.eatclone.model.TitleSubImage

interface DataManager : HawkHelper,FirebaseHelper{
    val getOnBoardingData:ArrayList<TitleSubImage>
}