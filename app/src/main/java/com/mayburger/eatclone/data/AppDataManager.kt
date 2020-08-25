package com.mayburger.eatclone.data

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mayburger.eatclone.R
import com.mayburger.eatclone.data.firebase.FirebaseHelper
import com.mayburger.eatclone.data.hawk.HawkHelper
import com.mayburger.eatclone.model.OnBoardingModel
import com.mayburger.eatclone.model.RegionDataModel
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.model.UserDataModel
import java.io.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(private val mContext: Context,
                                         private val mHawkHelper: HawkHelper,
                                         private val mFirebaseHelper: FirebaseHelper) : DataManager {
    override var isLoggedIn: Boolean
        get() = mHawkHelper.isLoggedIn
        set(value) {
            mHawkHelper.isLoggedIn = value
        }

    override fun createFirebaseUser(email: String, password: String): Task<AuthResult> {
        return mFirebaseHelper.createFirebaseUser(email,password)
    }

    override fun createFirestoreUser(user: UserDataModel, uuid: String): Task<Void> {
        return mFirebaseHelper.createFirestoreUser(user,uuid)
    }

    override fun getUserByEmail(email: String): Task<QuerySnapshot> {
        return mFirebaseHelper.getUserByEmail(email)
    }

    override fun createRestaurant(restaurantDataModel: RestaurantDataModel): Task<DocumentReference> {
        return mFirebaseHelper.createRestaurant(restaurantDataModel)
    }

    override fun updateRestaurant(restaurantDataModel: RestaurantDataModel): Task<Void> {
        return mFirebaseHelper.updateRestaurant(restaurantDataModel)
    }

    override fun getRestaurants(): Task<QuerySnapshot> {
        return mFirebaseHelper.getRestaurants()
    }

    override fun regions(): Task<QuerySnapshot> {
        return mFirebaseHelper.regions()
    }

    override fun setUserRegion(id: Int): Task<Void> {
        return mFirebaseHelper.setUserRegion(id)
    }

    override var region: RegionDataModel
        get() = mHawkHelper.region
        set(value) {
            mHawkHelper.region = value
        }
    override var user: UserDataModel
        get() = mHawkHelper.user
        set(value) {
            mHawkHelper.user = value
        }

    override val boardingData: ArrayList<OnBoardingModel>
        get() = Gson().fromJson<ArrayList<OnBoardingModel>>(getJsonStringFromRaw(R.raw.onboarding),object:
            TypeToken<ArrayList<OnBoardingModel>>(){}.type)

    fun getJsonStringFromRaw(rawInt:Int):String{
        val `is`: InputStream = mContext.resources.openRawResource(rawInt)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        `is`.use { `is` ->
            val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }
        return writer.toString()
    }



}