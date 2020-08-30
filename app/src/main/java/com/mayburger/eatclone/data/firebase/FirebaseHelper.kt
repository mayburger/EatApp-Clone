package com.mayburger.eatclone.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.model.UserDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMealViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.region.ItemRegionViewModel


interface FirebaseHelper {

    fun createFirebaseUser(email:String,password:String): Task<AuthResult>

    fun createFirestoreUser(user:UserDataModel,uuid:String):Task<Void>

    fun createRestaurant(restaurantDataModel: RestaurantDataModel):Task<DocumentReference>

//    fun createReservation()

    suspend fun signIn(email:String, password:String):AuthResult

    fun updateRestaurant(restaurantDataModel: RestaurantDataModel):Task<Void>

    suspend fun getRestaurants():ArrayList<ItemRestaurantViewModel>?

    suspend fun getMeals():ArrayList<ItemMealViewModel>?

    fun getUserByEmail(email:String):Task<QuerySnapshot>

    suspend fun getRegions():ArrayList<ItemRegionViewModel>?

    fun setUserRegion(id:Int):Task<Void>

}