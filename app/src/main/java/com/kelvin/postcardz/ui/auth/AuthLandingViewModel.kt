package com.kelvin.postcardz.ui.auth

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.annotations.JsonAdapter
import com.kelvin.postcardz.data.UserManager
import com.kelvin.postcardz.data.model.UserProfile
import com.kelvin.postcardz.ui.base.PostcardBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthLandingViewModel @Inject constructor(
    private val userManager: UserManager
) : PostcardBaseViewModel() {
    private val _emailError = MutableSharedFlow<String>()
    val emailError = _emailError.asSharedFlow()

    private val _passwordError = MutableSharedFlow<String>()
    val passwordError = _passwordError.asSharedFlow()

    private val _userNameError = MutableSharedFlow<String>()
    val userNameError: SharedFlow<String> = _userNameError

    private val _userAuthenticated = MutableSharedFlow<UserProfile?>()
    val userAuthenticated = _userAuthenticated.asSharedFlow()

    suspend fun validateLogin(email: String?, password: String?): Boolean {
        val emailErrorString = validEmail(email)
        _emailError.emit(emailErrorString)

        val passwordErrorString = validPassword(password)
        _passwordError.emit(passwordErrorString)

        return emailErrorString.isBlank() && passwordErrorString.isBlank()
    }

    fun loginUser(email: String, password: String) {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful && auth.currentUser != null) {
                val user = auth.currentUser!!
                getAuthUser(user.uid)
            } else {
                viewModelScope.launch {
                    _toastError.emit(it.exception)
                }
            }
        }
    }

    private fun getAuthUser(uid: String) {
        val db = Firebase.database
        db.getReference("ID_USERPROFILE").child(uid).get().addOnSuccessListener { task ->
            val userProfile = Gson().fromJson(task.value.toString(), UserProfile::class.java)
            viewModelScope.launch {
                val success = _userAuthenticated.emit(userProfile)
            }
        }.addOnFailureListener {
            _toastError.tryEmit(it)
        }
    }

    suspend fun validateRegister(email: String?, password: String?, userName: String?): Boolean {
        val emailErrorString = validEmail(email)
        _emailError.emit(emailErrorString)

        val passwordErrorString = validPassword(password)
        _passwordError.emit(passwordErrorString)

        val userNameErrorString = validUserName(userName)
        _userNameError.emit(userNameErrorString)

        return emailErrorString.isBlank() &&
                passwordErrorString.isBlank() &&
                userNameErrorString.isBlank()
    }

    fun registerUser(email: String, password: String, userName: String) {
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                auth.currentUser?.let {
                    Timber.d("Welcome ${it.email}")
                    val db = Firebase.database
                    val user = UserProfile(email, userName)
                    db.getReference("ID_USERPROFILE").child(it.uid).setValue(user)
                    viewModelScope.launch {
                        val success = _userAuthenticated.emit(user)
                    }
                }
            } else {
                _toastError.tryEmit(task.exception)
            }
        }
    }

    fun setAuthenticatedUser(user: UserProfile) {
        userManager.currentUser = user
    }

    private fun validEmail(email: String?): String {
        return when {
            email.isNullOrBlank() -> "Email is empty"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email is invalid"
            else -> ""
        }
    }

    private fun validPassword(password: String?): String {
        return if (password.isNullOrBlank())
            "Password is empty"
        else if (password.length < 6) {
            "Password needs to be at least 6 characters long"
        } else {
            ""
        }
    }

    private fun validUserName(userName: String?): String {
        return when {
            userName.isNullOrBlank() -> "UserName is empty"
            userName.length < 2 -> {
                "UserName needs to be at least 2 characters long"
            }
            userName.length > 15 -> {
                "UserName needs to be shorter than 15 characters to fit on postcard :)"
            }
            else -> {
                ""
            }
        }
    }
}