package com.mayburger.eatclone.data.hawk

import com.mayburger.eatclone.model.RegionDataModel
import com.mayburger.eatclone.model.UserDataModel


interface HawkHelper {

    var isLoggedIn:Boolean
    var region:RegionDataModel
    var user:UserDataModel

}