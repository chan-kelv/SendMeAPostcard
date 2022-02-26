package com.kelvin.bootstrap.hilt

import android.content.Context
import com.kelvin.bootstrap.util.TextResUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Named
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
}