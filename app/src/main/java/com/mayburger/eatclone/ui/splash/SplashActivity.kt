package com.mayburger.eatclone.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivitySplashBinding
import com.mayburger.eatclone.model.UserDataModel
import com.mayburger.eatclone.ui.base.BaseActivity
import com.mayburger.eatclone.ui.main.MainActivity
import com.mayburger.eatclone.ui.onboarding.OnBoardingActivity
import com.mayburger.eatclone.ui.region.SelectRegionActivity
import com.mayburger.eatclone.util.constants.FirebaseConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(),
    SplashNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel by viewModels()


    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SplashActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (FirebaseAuth.getInstance().currentUser != null) {
            Firebase.firestore.collection(FirebaseConstants.USERS)
                .document(FirebaseAuth.getInstance().currentUser?.uid ?: "").get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        viewModel.dataManager.user = it.result?.toObject<UserDataModel>() ?: UserDataModel()
                        if (viewModel.dataManager.region.id != null) {
                            MainActivity.startActivity(this)
                        } else {
                            SelectRegionActivity.startActivity(this)
                        }
                        finish()
                    } else {
                        viewModel.navigator?.onError(it.exception?.message)
                    }
                }
        } else {
            OnBoardingActivity.startActivity(this)
            finish()
        }
    }
}