package com.mayburger.eatclone.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayburger.eatclone.ui.adapters.MealAdapter
import com.mayburger.eatclone.ui.adapters.RestaurantAdapter
import com.mayburger.eatclone.ui.onboarding.OnBoardingAdapter
import com.mayburger.eatclone.ui.region.SelectRegionAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RecyclerModule {

    @Provides
    internal fun provideLinearLayoutManager(activity:Activity):LinearLayoutManager{
        return LinearLayoutManager(activity)
    }

    @Provides
    internal fun provideOnBoardingAdapter(activity:Activity): OnBoardingAdapter {
        return OnBoardingAdapter(activity as AppCompatActivity,4)
    }
    @Provides
    internal fun provideSelectRegionAdapter(): SelectRegionAdapter {
        return SelectRegionAdapter()
    }
    @Provides
    internal fun provideRestaurantAdapter(): RestaurantAdapter {
        return RestaurantAdapter()
    }

    @Provides
    internal fun provideMealAdapter():MealAdapter{
        return MealAdapter()
    }
//
//    @Provides
//    internal fun <T:ViewModel>provideAppAdapter(): AppAdapter<T> {
//        return AppAdapter()
//    }

}