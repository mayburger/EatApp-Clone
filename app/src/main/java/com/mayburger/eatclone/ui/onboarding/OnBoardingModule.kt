package com.mayburger.eatclone.ui.onboarding

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object OnBoardingModule {

    @Provides
    internal fun provideLinearLayoutManager(activity:Activity):LinearLayoutManager{
        return LinearLayoutManager(activity)
    }

    @Provides
    internal fun provideOnBoardingAdapter(activity:Activity):OnBoardingAdapter{
        return OnBoardingAdapter(activity as AppCompatActivity,4)
    }

}