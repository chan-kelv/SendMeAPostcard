package com.kelvin.PostcardZ.ui.auth

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kelvin.PostcardZ.R
import com.kelvin.PostcardZ.databinding.ActivityAuthBinding
import com.kelvin.PostcardZ.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity: BaseActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.auth_frag_container
        ) as NavHostFragment
        navController = navHostFragment.navController
    }
}