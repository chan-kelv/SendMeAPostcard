package com.kelvin.PostcardZ.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kelvin.PostcardZ.R
import com.kelvin.PostcardZ.databinding.FragmentAuthMainLandingBinding
import com.kelvin.PostcardZ.ui.base.PostcardBaseFragment
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