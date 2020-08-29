package com.mayburger.eatclone.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.model.UserDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMealViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.util.constants.FirebaseConstants
import kotlinx.coroutines.tasks.await
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

    override suspend fun getRestaurants(): ArrayList<ItemRestaurantViewModel>? {
        return try{
            val data = ArrayList<ItemRestaurantViewModel>()
            Firebase.firestore.collection(FirebaseConstants.RESTAURANTS).get().await()?.let { it ->
                it.map {
                    data.add(ItemRestaurantViewModel(it.toObject()))
                }
            }
            data
        } catch (e:java.lang.Exception){
            ArrayList()
        }
    }

    override suspend fun getMeals(): ArrayList<ItemMealViewModel>? {
        return try{
            val data = ArrayList<ItemMealViewModel>()
            Firebase.firestore.collection(FirebaseConstants.MEALS).get().await()?.let { it ->
                it.map {
                    data.add(ItemMealViewModel(it.toObject()))
                }
            }
            data
        } catch (e:java.lang.Exception){
            ArrayList()
        }
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