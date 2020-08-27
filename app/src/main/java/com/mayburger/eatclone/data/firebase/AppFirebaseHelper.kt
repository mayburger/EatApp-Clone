package com.mayburger.eatclone.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.model.UserDataModel
import com.mayburger.eatclone.util.constants.FirebaseConstants
import javax.inject.Inject


class AppFirebaseHelper @Inject constructor() : FirebaseHelper {


    private val TAG = this.javaClass.simpleName

    override fun createFirebaseUser(email: String, password: String): Task<AuthResult> {
        val mAuth = FirebaseAuth.getInstance()
        return mAuth.createUserWithEmailAndPassword(email, password)
    }

    override fun createFirestoreUser(user: UserDataModel, uuid: String): Task<Void> {
        val db = Firebase.firestore
        return db.collection(FirebaseConstants.USERS)
            .document(uuid)
            .set(user)
    }

    override fun getUserByEmail(email: String): Task<QuerySnapshot> {
        val db = Firebase.firestore
        return db.collection(FirebaseConstants.USERS).whereEqualTo(FirebaseConstants.EMAIL, email)
            .get()
    }

    override fun getRestaurants(limit: Int): Task<QuerySnapshot> {
        val restaurant = Firebase.firestore.collection(FirebaseConstants.RESTAURANTS)
        restaurant.limit(limit.toLong())
        return restaurant.get()
    }

    override fun getMeals(limit: Int): Task<QuerySnapshot> {
        val meals = Firebase.firestore.collection(FirebaseConstants.MEALS)
        meals.limit(limit.toLong())
        return meals.get()
    }

    override fun createRestaurant(restaurantDataModel: RestaurantDataModel): Task<DocumentReference> {
        return Firebase.firestore.collection(FirebaseConstants.RESTAURANTS).add(restaurantDataModel)
    }


    override fun updateRestaurant(restaurantDataModel: RestaurantDataModel): Task<Void> {
        return Firebase.firestore.collection(FirebaseConstants.RESTAURANTS)
            .document(restaurantDataModel.id ?: "").set(restaurantDataModel)
    }

    override fun regions(): Task<QuerySnapshot> {
        return Firebase.firestore.collection(FirebaseConstants.REGIONS).get()
    }

    override fun setUserRegion(id: Int): Task<Void> {
        return Firebase.firestore.collection(FirebaseConstants.USERS)
            .document(FirebaseAuth.getInstance().currentUser?.uid ?: "")
            .update(FirebaseConstants.REGION, id)
    }


}