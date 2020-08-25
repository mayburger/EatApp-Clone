package com.mayburger.eatclone.model

import com.google.gson.annotations.SerializedName

data class RegionDataModel(
    @SerializedName("id")
    val id:String? = null,
    @SerializedName("name")
    val name:String? = null,
    @SerializedName("country")
    val country:String? = null,
    @SerializedName("image")
    val image:String? = null
)