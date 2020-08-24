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
import com.mayburger.eatclone.model.TitleSubImage
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

    override fun createFirestoreUser(user: UserDataModel): Task<DocumentReference> {
        return mFirebaseHelper.createFirestoreUser(user)
    }

    override fun checkFirestoreUser(email: String): Task<QuerySnapshot> {
        return mFirebaseHelper.checkFirestoreUser(email)
    }

    override var region: String
        get() = mHawkHelper.region
        set(value) {
            mHawkHelper.region = value
        }

    override val getOnBoardingData: ArrayList<TitleSubImage>
        get() = Gson().fromJson<ArrayList<TitleSubImage>>(getJsonStringFromRaw(R.raw.onboarding),object:
            TypeToken<ArrayList<TitleSubImage>>(){}.type)

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