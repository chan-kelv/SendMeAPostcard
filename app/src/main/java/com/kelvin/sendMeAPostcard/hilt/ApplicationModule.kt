package com.kelvin.sendMeAPostcard.hilt

import android.content.Context
import com.kelvin.sendMeAPostcard.data.preference.UserSettingsSharedPrefManager
import com.kelvin.sendMeAPostcard.util.TextResUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Application component
object ApplicationModule {
    @Singleton
    @Provides
    fun providesDummyString() = "Hello World!"

    @Singleton
    @Provides
    fun providesTextResUtil(@ApplicationContext context: Context) = TextResUtil(context)

    @Singleton
    @Provides
    fun providesUserSharedPreference(@ApplicationContext context: Context) =
        UserSettingsSharedPrefManager(context)
}