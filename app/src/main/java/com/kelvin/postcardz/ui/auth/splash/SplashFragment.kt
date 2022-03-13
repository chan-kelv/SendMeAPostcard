package com.kelvin.postcardz.ui.auth.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.kelvin.postcardz.databinding.FragmentSplashLoadingBinding
import com.kelvin.postcardz.ui.HomeActivity
import com.kelvin.postcardz.ui.auth.AuthLandingFragment.Companion.navigateToAuthLandingFragment
import com.kelvin.postcardz.ui.base.PostcardBaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    splashViewModel.foundAuthUser.collect { userFound ->
                        if (userFound) {
                            val intent = Intent(requireActivity(), HomeActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        } else {
                            navigateToAuthLandingFragment()
                        }
                    }
                }
            }
        }
        return splashViewBinding.root
    }

    override fun onResume() {
        super.onResume()
        splashViewModel.viewModelScope.launch {
            splashViewModel.attemptRetrieveUser()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): SplashFragment {
            return SplashFragment()
        }
    }
}