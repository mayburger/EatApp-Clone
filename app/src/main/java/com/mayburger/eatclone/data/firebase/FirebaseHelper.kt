package com.mayburger.eatclone.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.model.UserDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMealViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel


interface FirebaseHelper {

    fun createFirebaseUser(email:String,password:String): Task<AuthResult>

    fun createFirestoreUser(user:UserDataModel,uuid:String):Task<Void>

    fun createRestaurant(restaurantDataModel: RestaurantDataModel):Task<DocumentReference>

//    fun createReservation()

    fun updateRestaurant(restaurantDataModel: RestaurantDataModel):Task<Void>

    suspend fun getRestaurants():ArrayList<ItemRestaurantViewModel>?

    suspend fun getMeals():ArrayList<ItemMealViewModel>?

    fun getUserByEmail(email:String):Task<QuerySnapshot>

    fun regions():Task<QuerySnapshot>

    fun setUserRegion(id:Int):Task<Void>

}