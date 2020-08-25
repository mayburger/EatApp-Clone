package com.mayburger.eatclone.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RestaurantDataModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("address")
    var address: String? = null,
    @SerializedName("cuisine")
    var cuisine: String? = null,
    @SerializedName("distance")
    var distance: String? = null,
    @SerializedName("price")
    var _price: Int? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("tags")
    var tags: ArrayList<TagDataModel>? = null
) : Serializable {
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
