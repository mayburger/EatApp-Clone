package com.mayburger.eatclone.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuDataModel(
    @SerializedName("id")
    val id:String? = null,
    @SerializedName("name")
    val name:String? = null,
    @SerializedName("currency")
    val currency:String? = null,
    @SerializedName("price")
    val price:Int = 0,
    @SerializedName("image")
    val image:String? = null,
    @SerializedName("quantity")
    var quantity:Int = 0
): Parcelable