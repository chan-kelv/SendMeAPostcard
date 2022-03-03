package com.kelvin.sendMeAPostcard.ui.auth

import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.kelvin.sendMeAPostcard.R
import com.kelvin.sendMeAPostcard.databinding.ActivityAuthBinding
import com.kelvin.sendMeAPostcard.ui.base.BaseActivity
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