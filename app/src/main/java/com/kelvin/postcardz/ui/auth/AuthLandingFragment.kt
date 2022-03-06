package com.kelvin.postcardz.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.kelvin.postcardz.R
import com.kelvin.postcardz.databinding.FragmentAuthMainLandingBinding
import com.kelvin.postcardz.ui.base.PostcardBaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthLandingFragment: PostcardBaseFragment() {
    private var _authLandingViewBinding: FragmentAuthMainLandingBinding? = null
    private val authViewBinding get() = _authLandingViewBinding!!

    private val authViewModel: AuthLandingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _authLandingViewBinding = FragmentAuthMainLandingBinding.inflate(inflater, container, false)
        val view = authViewBinding.root

        setupViewBindings(authViewModel, authViewBinding)

        authViewBinding.loginButton.setOnClickListener(loginClickHandler)
        authViewBinding.registerButton.setOnClickListener(registerClickHandler)

        return view
    }

    private val loginClickHandler = View.OnClickListener {
        val emailText = authViewBinding.emailInput.text.toString()
        val passwordText = authViewBinding.passwordInput.text.toString()
        authViewModel.viewModelScope.launch {
            authViewModel.validateLogin(emailText, passwordText)
        }
    }

    private val registerClickHandler = View.OnClickListener {
        if (authViewBinding.registerGroup.visibility != View.VISIBLE) {
            authViewBinding.registerGroup.visibility = View.VISIBLE
            authViewBinding.usernameInput.setText("")
            return@OnClickListener
        }

        val emailText = authViewBinding.emailInput.text.toString()
        val passwordText = authViewBinding.passwordInput.text.toString()
        val userNameText = authViewBinding.usernameInput.text.toString()
        authViewModel.viewModelScope.launch {
            val registerInputValid =  authViewModel.validateRegister(emailText, passwordText, userNameText)
        }
    }

    companion object {
        fun PostcardBaseFragment.navigateToAuthLandingFragment() {
            findSafeNavController().navigate(R.id.action_splashFragment_to_authLandingFragment)
        }
    }
}