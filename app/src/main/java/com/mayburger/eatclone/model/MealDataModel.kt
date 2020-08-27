package com.mayburger.eatclone.model

import com.google.gson.annotations.SerializedName

data class MealDataModel(
    @SerializedName("id")
    val id:String? = null,
    @SerializedName("name")
    val name:String? = null,
    @SerializedName("image")
    val image:String? = null
)