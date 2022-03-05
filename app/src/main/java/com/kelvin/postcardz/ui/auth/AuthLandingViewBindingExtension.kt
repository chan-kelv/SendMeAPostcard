package com.kelvin.postcardz.ui.auth

import com.kelvin.postcardz.databinding.FragmentAuthMainLandingBinding

fun AuthLandingFragment.setupViewBindings(
    authViewModel: AuthLandingViewModel,
    authBinding: FragmentAuthMainLandingBinding
) {
    authViewModel.emailError.observe(this) {
        authBinding.emailLayout.error = it
    }
    authViewModel.passwordError.observe(this) {
        authBinding.passwordLayout.error = it
    }
    authViewModel.userNameError.observe(this) {
        authBinding.usernameLayout.error = it
    }
}