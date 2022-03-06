package com.kelvin.postcardz.ui.auth

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kelvin.postcardz.databinding.FragmentAuthMainLandingBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun AuthLandingFragment.setupViewBindings(
    authViewModel: AuthLandingViewModel,
    authBinding: FragmentAuthMainLandingBinding
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                authViewModel.emailError.collect {
                    authBinding.emailLayout.error = it
                }
            }
            launch {
                authViewModel.passwordError.collect {
                    authBinding.passwordLayout.error = it
                }
            }
            launch {
                authViewModel.userNameError.collectLatest {
                    authBinding.usernameLayout.error = it
                }
            }
        }
    }
}