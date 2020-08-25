package com.mayburger.eatclone.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TagDataModel(
    @SerializedName("id")
    var id:Int? = null,
    @SerializedName("name")
    var name:String? = null
):Serializable