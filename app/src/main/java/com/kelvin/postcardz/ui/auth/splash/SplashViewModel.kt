package com.kelvin.postcardz.ui.auth.splash

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import com.kelvin.postcardz.data.UserManager
import com.kelvin.postcardz.data.model.UserProfile
import com.kelvin.postcardz.data.preference.UserSettingsSharedPrefManager
import com.kelvin.postcardz.ui.base.PostcardBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userManager: UserManager
) : PostcardBaseViewModel() {
    private val _foundAuthUser = MutableSharedFlow<Boolean>()
    val foundAuthUser = _foundAuthUser.asSharedFlow()

    /**
     * Determins if firebase user is saved. If it is, try to retrieve user from it and
     * go directly to HOME if success, else go to login flow
     */
    suspend fun attemptRetrieveUser() {
        val fbUser = FirebaseAuth.getInstance().currentUser
        if (fbUser != null) {
            val fbDatabase =
                FirebaseDatabase.getInstance().getReference("ID_USERPROFILE").child(fbUser.uid)
                    .get().addOnSuccessListener {
                        val userProfile = Gson().fromJson(it.value.toString(), UserProfile::class.java)
                        if (userProfile != null) {
                            userManager.currentUser = userProfile
                        }

                        viewModelScope.launch {
                            _foundAuthUser.emit(userProfile != null)
                        }
                    }.addOnFailureListener {
                        viewModelScope.launch {
                            _foundAuthUser.emit(false)
                        }
                    }
        } else {
            _foundAuthUser.emit(false)
        }
    }
}