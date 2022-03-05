package com.kelvin.postcardz.ui.auth

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kelvin.postcardz.ui.base.PostcardBaseViewModel

class AuthLandingViewModel: PostcardBaseViewModel() {
    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError

    private val _userNameError = MutableLiveData<String>()
    val userNameError: LiveData<String> = _userNameError

    fun validateLogin(email: String?, password: String?): Boolean {
        val emailErrorString = validEmail(email)
        _emailError.value = emailErrorString

        val passwordErrorString = validPassword(password)
        _passwordError.value = passwordErrorString

        return emailErrorString.isBlank() && passwordErrorString.isBlank()
    }

    fun validateRegister(email: String?, password: String?, userName: String?): Boolean {
        val emailErrorString = validEmail(email)
        _emailError.value = emailErrorString

        val passwordErrorString = validPassword(password)
        _passwordError.value = passwordErrorString

        val userNameErrorString = validUserName(userName)
        _userNameError.value = userNameErrorString

        return emailErrorString.isBlank() &&
                passwordErrorString.isBlank() &&
                userNameErrorString.isBlank()
    }

    private fun validEmail(email: String?): String {
        return when {
            email.isNullOrBlank() -> "Email is empty"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email is invalid"
            else -> ""
        }
    }

    private fun validPassword(password: String?) : String {
        return if (password.isNullOrBlank())
            "Password is empty"
        else if (password.length < 6){
            "Password needs to be at least 6 characters long"
        } else {
            ""
        }
    }

    private fun validUserName(userName: String?) : String {
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