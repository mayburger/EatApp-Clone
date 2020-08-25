package com.mayburger.eatclone.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.model.UserDataModel


interface FirebaseHelper {

    fun createFirebaseUser(email:String,password:String): Task<AuthResult>

    fun createFirestoreUser(user:UserDataModel,uuid:String):Task<Void>

    fun createRestaurant(restaurantDataModel: RestaurantDataModel):Task<DocumentReference>
    fun updateRestaurant(restaurantDataModel: RestaurantDataModel):Task<Void>

    fun getRestaurants():Task<QuerySnapshot>

    fun getUserByEmail(email:String):Task<QuerySnapshot>

    fun regions():Task<QuerySnapshot>

    fun setUserRegion(id:Int):Task<Void>

}