package com.mayburger.eatclone.di

import android.app.Application
import android.content.Context
import com.mayburger.eatclone.data.AppDataManager
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.data.firebase.AppFirebaseHelper
import com.mayburger.eatclone.data.firebase.FirebaseHelper
import com.mayburger.eatclone.data.hawk.AppHawkHelper
import com.mayburger.eatclone.data.hawk.HawkHelper
import com.mayburger.eatclone.util.rx.AppSchedulerProvider
import com.mayburger.eatclone.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideHawkHelper(appHawkHelper: AppHawkHelper): HawkHelper = appHawkHelper

    @Provides
    @Singleton
    internal fun provideFirebaseHelper(appFirebaseHelper: AppFirebaseHelper): FirebaseHelper = appFirebaseHelper

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}