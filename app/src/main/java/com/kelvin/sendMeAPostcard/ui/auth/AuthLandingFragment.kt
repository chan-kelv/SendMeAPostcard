package com.kelvin.sendMeAPostcard.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kelvin.sendMeAPostcard.R
import com.kelvin.sendMeAPostcard.databinding.FragmentAuthMainLandingBinding
import com.kelvin.sendMeAPostcard.ui.base.PostcardBaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthLandingFragment: PostcardBaseFragment() {
    private var _authLandingViewBinding: FragmentAuthMainLandingBinding? = null
    private val authLandingViewBinding get() = _authLandingViewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _authLandingViewBinding = FragmentAuthMainLandingBinding.inflate(inflater, container, false)
        return authLandingViewBinding.root
    }

    companion object {
        fun PostcardBaseFragment.navigateToAuthLandingFragment() {
            findSafeNavController().navigate(R.id.action_splashFragment_to_authLandingFragment)
        }
    }
}