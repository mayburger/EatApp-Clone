package com.mayburger.eatclone.model

import com.google.gson.annotations.SerializedName

data class UserDataModel(
    @SerializedName("email")
    val email:String? = null,
    @SerializedName("password")
    val password:String? = null,
    @SerializedName("fullname")
    val fullName:String? = null,
    @SerializedName("phone")
    val phone:String? = null
){
    @SerializedName("region")
    val region:String? = null
}