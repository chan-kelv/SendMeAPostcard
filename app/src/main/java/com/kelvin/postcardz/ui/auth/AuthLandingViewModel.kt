package com.kelvin.postcardz.ui.auth

import android.util.Patterns
import com.kelvin.postcardz.ui.base.PostcardBaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow

class AuthLandingViewModel: PostcardBaseViewModel() {
    private val _emailError = MutableSharedFlow<String>()
    val emailError = _emailError.asSharedFlow()

    private val _passwordError = MutableSharedFlow<String>()
    val passwordError = _passwordError.asSharedFlow()

    private val _userNameError = MutableSharedFlow<String>()
    val userNameError: SharedFlow<String> = _userNameError

    suspend fun validateLogin(email: String?, password: String?): Boolean {
        val emailErrorString = validEmail(email)
        _emailError.emit(emailErrorString)

        val passwordErrorString = validPassword(password)
        _passwordError.emit(passwordErrorString)

        return emailErrorString.isBlank() && passwordErrorString.isBlank()
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