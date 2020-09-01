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
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemCategoryViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.region.ItemRegionViewModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMenuViewModel
import java.io.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class AppDataManager @Inject constructor(private val mContext: Context,
                                         private val mHawkHelper: HawkHelper,
                                         private val mFirebaseHelper: FirebaseHelper) : DataManager {

    override val boardingData: ArrayList<OnBoardingModel>
        get() = Gson().fromJson<ArrayList<OnBoardingModel>>(getJsonStringFromRaw(R.raw.onboarding),object:
            TypeToken<ArrayList<OnBoardingModel>>(){}.type)

    override fun get30Days(): ArrayList<Date> {
        val dates = ArrayList<Date>()
        val calendar = Calendar.getInstance()
        for (i in 0..30) {
            dates.add(calendar.time)
            calendar.add(Calendar.DATE,1)
        }
        return dates
    }

    override suspend fun getMenus(restaurantId: String?): ArrayList<ItemMenuViewModel>? {
        return mFirebaseHelper.getMenus(restaurantId)
    }

    override fun getAvailableTimes(): ArrayList<Date> {
        val dates = ArrayList<Date>()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR,8)
        calendar.set(Calendar.MINUTE,30)
        for(i in 0..30){
            dates.add(calendar.time)
            calendar.add(Calendar.MINUTE,30)
        }
        return dates
    }

    override suspend fun getRestaurant(id: String): RestaurantDataModel? {
        return mFirebaseHelper.getRestaurant(id)
    }

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

    override suspend fun getRestaurants(): ArrayList<ItemRestaurantViewModel>? {
        return mFirebaseHelper.getRestaurants()
    }

    override suspend fun getCategories(): ArrayList<ItemCategoryViewModel>? {
        return mFirebaseHelper.getCategories()
    }

    override suspend fun getRegions(): ArrayList<ItemRegionViewModel>? {
        return mFirebaseHelper.getRegions()
    }

    override fun setUserRegion(id: Int): Task<Void> {
        return mFirebaseHelper.setUserRegion(id)
    }

    override suspend fun signIn(email: String, password: String): AuthResult {
        return mFirebaseHelper.signIn(email,password)
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