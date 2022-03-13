package com.kelvin.postcardz.data

import com.google.firebase.auth.FirebaseAuth
import com.kelvin.postcardz.data.model.UserProfile
import com.kelvin.postcardz.data.preference.UserSettingsSharedPrefManager

class UserManager() {
    var currentUser: UserProfile? = null

    fun clearCurrentUser() {
        val fb = FirebaseAuth.getInstance()
        fb.signOut()
        currentUser = null
    }
}