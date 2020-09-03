package com.mayburger.eatclone.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.mayburger.eatclone.model.OrderDataModel
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.model.UserDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemCategoryViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMenuViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.region.ItemRegionViewModel


interface FirebaseHelper {

    fun createFirebaseUser(email:String,password:String): Task<AuthResult>

    fun createFirestoreUser(user:UserDataModel,uuid:String):Task<Void>

    fun createRestaurant(restaurantDataModel: RestaurantDataModel):Task<DocumentReference>

    suspend fun createOrder(orderDataModel: OrderDataModel):DocumentReference?

    suspend fun updateOrder(orderDataModel: OrderDataModel):Void?

    suspend fun hasOngoingOrder():Boolean

    fun updateRestaurant(restaurantDataModel: RestaurantDataModel):Task<Void>

    suspend fun signIn(email:String, password:String):AuthResult

    suspend fun getRestaurants():ArrayList<ItemRestaurantViewModel>?

    suspend fun getRestaurant(id:String):RestaurantDataModel?

    suspend fun getCategories():ArrayList<ItemCategoryViewModel>?

    suspend fun getMenus(restaurantId:String?):ArrayList<ItemMenuViewModel>?

    fun getUserByEmail(email:String):Task<QuerySnapshot>

    suspend fun getRegions():ArrayList<ItemRegionViewModel>?

    fun setUserRegion(id:Int):Task<Void>

}