package com.kelvin.postcardz.ui.auth

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kelvin.postcardz.databinding.FragmentAuthMainLandingBinding
import com.kelvin.postcardz.ui.HomeActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun AuthLandingFragment.setupViewBindingAndModelCollection(
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
            launch {
                authViewModel.toastError.collect {
                    Toast.makeText(this@setupViewBindingAndModelCollection.requireContext(),
                    "Error: $it",
                    Toast.LENGTH_LONG).show()
                }
            }
            launch {
                authViewModel.userAuthenticated.collectLatest { userProfile ->
                    userProfile?.let {
                        authViewModel.setAuthenticatedUser(it)
                        val intent = Intent(this@setupViewBindingAndModelCollection.requireActivity(), HomeActivity::class.java)
                        startActivity(intent)
                        this@setupViewBindingAndModelCollection.requireActivity().finish()
                    } ?: run {
                        // user error - reset everything and try again
                    }
                }
            }
        }
    }
}