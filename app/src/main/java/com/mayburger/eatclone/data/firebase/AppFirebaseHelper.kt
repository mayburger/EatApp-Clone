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
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemCategoryViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.region.ItemRegionViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMenuViewModel
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
        return try {
            val data = ArrayList<ItemRestaurantViewModel>()
            Firebase.firestore.collection(FirebaseConstants.RESTAURANTS).get().await()?.let { it ->
                it.map {
                    data.add(ItemRestaurantViewModel(it.toObject()))
                }
            }
            data
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    override suspend fun getRestaurant(id: String): RestaurantDataModel? {
        return try {
            Firebase.firestore.collection(FirebaseConstants.RESTAURANTS).document(id).get().await()
                .toObject<RestaurantDataModel>()
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    override suspend fun getMenus(restaurantId: String?): ArrayList<ItemMenuViewModel>? {
        return try {
            val data = ArrayList<ItemMenuViewModel>()
            if (restaurantId != null) {
                Firebase.firestore.collection(FirebaseConstants.RESTAURANTS).document(restaurantId).collection(FirebaseConstants.MENU).get().await()?.let { it ->
                    it.map {
                        data.add(
                            ItemMenuViewModel(
                                it.toObject()
                            )
                        )
                    }
                }
            }
            data
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    override suspend fun signIn(email: String, password: String): AuthResult {
        return try {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getCategories(): ArrayList<ItemCategoryViewModel>? {
        return try {
            val data = ArrayList<ItemCategoryViewModel>()
            Firebase.firestore.collection(FirebaseConstants.CATEGORY).get().await()?.let { it ->
                it.map {
                    data.add(ItemCategoryViewModel(it.toObject()))
                }
            }
            data
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    override fun createRestaurant(restaurantDataModel: RestaurantDataModel): Task<DocumentReference> {
        return Firebase.firestore.collection(FirebaseConstants.RESTAURANTS).add(restaurantDataModel)
    }


    override fun updateRestaurant(restaurantDataModel: RestaurantDataModel): Task<Void> {
        return Firebase.firestore.collection(FirebaseConstants.RESTAURANTS)
            .document(restaurantDataModel.id ?: "").set(restaurantDataModel)
    }

    override suspend fun getRegions(): ArrayList<ItemRegionViewModel>? {
        return try {
            val data = ArrayList<ItemRegionViewModel>()
            Firebase.firestore.collection(FirebaseConstants.REGIONS).get().await()?.let { it ->
                it.map {
                    data.add(ItemRegionViewModel(it.toObject()))
                }
            }
            data
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    override fun setUserRegion(id: Int): Task<Void> {
        return Firebase.firestore.collection(FirebaseConstants.USERS)
            .document(FirebaseAuth.getInstance().currentUser?.uid ?: "")
            .update(FirebaseConstants.REGION, id)
    }


}