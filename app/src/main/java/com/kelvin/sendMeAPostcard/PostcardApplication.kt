package com.kelvin.sendMeAPostcard

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PostcardApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Timber init
        Timber.plant(Timber.DebugTree())
    }
}
