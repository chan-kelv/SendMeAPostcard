package com.kelvin.postcardz.ui.auth.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kelvin.postcardz.databinding.FragmentSplashLoadingBinding
import com.kelvin.postcardz.ui.auth.AuthLandingFragment.Companion.navigateToAuthLandingFragment
import com.kelvin.postcardz.ui.base.PostcardBaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : PostcardBaseFragment() {
    private var _splashViewBinding: FragmentSplashLoadingBinding? = null
    private val splashViewBinding get() = _splashViewBinding!!

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _splashViewBinding = FragmentSplashLoadingBinding.inflate(inflater, container, false)
        return splashViewBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (splashViewModel.isUserSetup()) {
            navigateToAuthLandingFragment()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): SplashFragment {
            return SplashFragment()
        }
    }
}