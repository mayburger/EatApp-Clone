package com.mayburger.eatclone.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurantDataModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("address")
    var address: String? = null,
    @SerializedName("notes")
    var notes: String? = null,
    @SerializedName("cuisine")
    var cuisine: String? = null,
    @SerializedName("distance")
    var distance: String? = null,
    @SerializedName("price")
    var _price: Int? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("tags")
    var tags: ArrayList<TagDataModel>? = null

) : Parcelable {
    fun price(): String {
        return when (_price) {
            1 -> {
                "$$"
            }
            2 -> {
                "$$$"
            }
            else -> {
                "$"
            }
        }
    }
}
