package com.mayburger.eatclone.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderDataModel(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("menu")
    val menu: ArrayList<MenuDataModel>? = ArrayList(),
    @SerializedName("restaurant_id")
    val restaurantId: String? = null,
    @SerializedName("user_id")
    val userId: String? = null,
    @SerializedName("subtotal")
    val subtotal: Int = 0,
    @SerializedName("status")
    val status:Int? = 0
) : Parcelable{
    companion object{
     @IgnoredOnParcel
     val USER_ID = "userId"
    @IgnoredOnParcel
    val STATUS = "status"
    }
}