package com.kelvin.sendMeAPostcard.data.preference

import android.app.Activity
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserSettingsSharedPrefManager(
    @ApplicationContext private val context: Context
): BaseSharedPref(context, PREF_USER_SETTINGS_FILE) {
    fun isUsersFirstTime(): Boolean {
        getSharedPref()?.let { pref ->
            return pref.getBoolean(PREF_USER_FIRST_TIME_KEY, true)
        }
        return true
    }

    fun recordUsersFirstTime(isFirstTime: Boolean = false) {
        getSharedPref()?.let { pref ->
            with(pref.edit()) {
                putBoolean(PREF_USER_FIRST_TIME_KEY, isFirstTime)
                apply()
            }
        }
    }
}