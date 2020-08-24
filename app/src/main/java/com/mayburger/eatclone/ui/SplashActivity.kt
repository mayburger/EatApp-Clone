package com.mayburger.eatclone.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.mayburger.eatclone.R
import com.mayburger.eatclone.ui.main.MainActivity
import com.mayburger.eatclone.ui.onboarding.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (FirebaseAuth.getInstance().currentUser != null) {
            MainActivity.startActivity(this)
        } else {
            OnBoardingActivity.startActivity(this)
        }
        finish()
    }
}