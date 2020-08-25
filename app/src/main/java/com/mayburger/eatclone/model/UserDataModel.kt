package com.mayburger.eatclone.model

import com.google.gson.annotations.SerializedName

data class UserDataModel(
    @SerializedName("uid")
    var uid:String? = null,
    @SerializedName("email")
    var email:String? = null,
    @SerializedName("password")
    var password:String? = null,
    @SerializedName("fullname")
    var fullName:String? = null,
    @SerializedName("phone")
    var phone:String? = null
){
    @SerializedName("region")
    var region:Long? = null
}