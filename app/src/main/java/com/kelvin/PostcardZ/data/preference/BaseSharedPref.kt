package com.kelvin.PostcardZ.data.preference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

abstract class BaseSharedPref (
    private val context: Context,
    private val prefFileName: String?
) {
    fun getSharedPref(mode: Int = Context.MODE_PRIVATE): SharedPreferences? {
        return if (!prefFileName.isNullOrBlank()) {
            context.getSharedPreferences(prefFileName, mode)
        } else if (context is Activity) {
            context.getPreferences(mode)
        } else {
            null
        }
    }

    companion object {
        // User Preference keys
        const val PREF_USER_SETTINGS_FILE = "USER_SETTINGS_PREF"
        const val PREF_USER_FIRST_TIME_KEY = "IS_USERS_FIRST_TIME"
    }
}