package com.kelvin.postcardz.ui.auth.splash

import com.kelvin.postcardz.data.preference.UserSettingsSharedPrefManager
import com.kelvin.postcardz.ui.base.PostcardBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashRepo: SplashRepository
): PostcardBaseViewModel(){
    fun isUserSetup(): Boolean {
        return splashRepo.isUserSignedIn()
    }

    // TODO simple repo not worth making its own class for now?
    class SplashRepository @Inject constructor(
        private val userPref: UserSettingsSharedPrefManager
    ) {
        fun isUserSignedIn(): Boolean {
            return userPref.isUsersFirstTime()
        }
    }
}