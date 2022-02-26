package com.kelvin.bootstrap.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import com.kelvin.bootstrap.R
import com.kelvin.bootstrap.databinding.ActivityMainBinding
import com.kelvin.bootstrap.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var navLiveData: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupNav()
    }

    private fun setupNav() {
        val navGraphIds = listOf(R.navigation.nav_left_tab, R.navigation.nav_right_tab)
        val navLiveData = binding.bottomNavMain.setupWithNavController(
            navGraphIds,
            supportFragmentManager,
            binding.fragContainerMain.id,
            intent)
        navLiveData.observe(this) { navController ->
            Timber.d(navController.graph.label?.toString() ?: "")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navLiveData?.value?.navigateUp() ?: false
    }
}